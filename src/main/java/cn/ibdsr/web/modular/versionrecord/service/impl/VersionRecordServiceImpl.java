package cn.ibdsr.web.modular.versionrecord.service.impl;

import cn.ibdsr.clientanalysis.service.IClientAnalysisService;
import cn.ibdsr.core.base.service.impl.AbstractBaseService;
import cn.ibdsr.core.util.ToolUtil;
import cn.ibdsr.fastdfs.base.service.ImageService;
import cn.ibdsr.web.common.constant.factory.PageFactory;
import cn.ibdsr.web.common.constant.state.IsDeleted;
import cn.ibdsr.web.common.constant.state.SoftwareType;
import cn.ibdsr.web.common.exception.BizExceptionEnum;
import cn.ibdsr.web.common.exception.BussinessException;
import cn.ibdsr.web.common.persistence.dao.SoftwareMapper;
import cn.ibdsr.web.common.persistence.dao.UploadVersionRecordMapper;
import cn.ibdsr.web.common.persistence.model.Software;
import cn.ibdsr.web.common.persistence.model.UploadVersionRecord;
import cn.ibdsr.web.core.shiro.ShiroKit;
import cn.ibdsr.web.core.util.ImageUtil;
import cn.ibdsr.web.core.util.VersionUtil;
import cn.ibdsr.web.modular.versionrecord.dao.VersionRecordDao;
import cn.ibdsr.web.modular.versionrecord.service.IVersionRecordService;
import cn.ibdsr.web.modular.versionrecord.transfer.VersionRecordDTO;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 版本记录Service
 * @Version V1.0
 * @CreateDate 2019/7/11 10:31
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Wujiayun            类说明
 */
@Service
public class VersionRecordServiceImpl extends AbstractBaseService<VersionRecordDTO, UploadVersionRecord> implements IVersionRecordService {
    @Resource
    private VersionRecordDao versionRecordDao;

    @Resource
    private SoftwareMapper softwareMapper;

    @Resource
    private ImageService imageService;

    @Resource
    private UploadVersionRecordMapper uploadVersionRecordMapper;

    @Resource
    private IClientAnalysisService clientAnalysisService;

    @Override
    public Page<JSONObject> list(Long softwareId){
        Page<JSONObject> page = new PageFactory<JSONObject>().defaultPage();
        List<JSONObject> list = versionRecordDao.list(page, softwareId);
        for (JSONObject jObj :
                list) {
            jObj.put("sizeMB", BigDecimal.valueOf(jObj.getLong("size"))
                    .divide(BigDecimal.valueOf(1024))
                    .divide(BigDecimal.valueOf(1024))
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                    .doubleValue() + "MB");

        }
        page.setRecords(list);
        return page;
    }

    @Override
    public Map<String, Object> analyse(MultipartFile file, Long softwareId){
        Software software = checkSoftwareId(softwareId);
        checkFile(file, software.getType());

        Map<String, Object> pkgInfoMap = clientAnalysisService.analyse(file);
        if (ToolUtil.isEmpty(pkgInfoMap)) {
            throw new BussinessException(BizExceptionEnum.ANALYSE_ERROR);
        }
        return pkgInfoMap;
    }

    @Override
    public void download(Long id, HttpServletResponse response) {
        UploadVersionRecord uploadVersionRecord = uploadVersionRecordMapper.selectById(id);
        if (uploadVersionRecord == null) {
            throw new BussinessException(BizExceptionEnum.UPLOAD_VERSION_RECORD_NOT_EXIST);
        }

        clientAnalysisService.download(ImageUtil.PREFIX_IMAGE_URL + uploadVersionRecord.getUrl(),
                uploadVersionRecord.getFilename(), response);
    }

    /**
     * 校验软件ID，并获取软件信息
     *
     * @param softwareId 软件ID
     * @return
     */
    private Software checkSoftwareId(Long softwareId) {
        if (ToolUtil.isEmpty(softwareId)) {
            throw new BussinessException(BizExceptionEnum.SOFTWARE_ID_IS_NULL);
        }
        Software software = softwareMapper.selectById(softwareId);
        if (software == null) {
            throw new BussinessException(BizExceptionEnum.SOFTWARE_IS_NOT_EXIST);
        }
        return software;
    }

    /**
     * 校验文件
     * @param file
     */
    private void checkFile(MultipartFile file, Integer type) {
        if (file == null || file.isEmpty()){
            throw new BussinessException(BizExceptionEnum.FILE_IS_NULL);
        }
        if (file.getOriginalFilename() == null || !file.getOriginalFilename().endsWith(SoftwareType.valueOf(type))) {
            throw new BussinessException(BizExceptionEnum.FILE_TYPE_ERROR);
        }
        long size = file.getSize();
        if (size > 100 * 1024 * 1024) { // 文件超出100M，抛出异常
            throw new BussinessException(BizExceptionEnum.FILE_TOO_BIG);
        }
    }

    @Override
    public BaseMapper<UploadVersionRecord> getMapper() {
        return uploadVersionRecordMapper;
    }

    @Override
    public UploadVersionRecord getConversionDO() {
        return new UploadVersionRecord();
    }

    @Override
    public VersionRecordDTO getConversionDTO() {
        return new VersionRecordDTO();
    }

    @Override
    @Transactional
    public void checkInsert(VersionRecordDTO versionRecordDTO) {
        checkVersionRecordDTO(versionRecordDTO);

        versionRecordDTO.setCreatedTime(new Date());
        versionRecordDTO.setModifiedTime(new Date());
        versionRecordDTO.setCreatedUser(ShiroKit.getUser().getId());
        versionRecordDTO.setModifiedUser(ShiroKit.getUser().getId());
        versionRecordDTO.setIsDeleted(IsDeleted.NORMAL.getCode());

        Software software = softwareMapper.selectById(versionRecordDTO.getSoftwareId());
        software.setAppName(versionRecordDTO.getAppName());
        software.setFilename(versionRecordDTO.getFilename());
        software.setPackageName(versionRecordDTO.getPackageName());
        software.setVersionNo(versionRecordDTO.getVersionNo());
        software.setSize(versionRecordDTO.getSize());
        software.setUrl(versionRecordDTO.getUrl());
        software.setIsForce(versionRecordDTO.getIsForce());
        software.setModifiedTime(new Date());
        software.setModifiedUser(ShiroKit.getUser().getId());
        software.updateById();
    }

    /**
     * 校验上传的软件信息
     * @param versionRecordDTO
     */
    private void checkVersionRecordDTO(VersionRecordDTO versionRecordDTO) {
        Software software = softwareMapper.selectById(versionRecordDTO.getSoftwareId());
        if (software.getVersionNo() != null) {
            if (VersionUtil.changeVersionNo(software.getVersionNo()) >= VersionUtil.changeVersionNo(versionRecordDTO.getVersionNo())) {
                throw new BussinessException(BizExceptionEnum.NEW_VERSION_SMALL_OLD_VERSION);
            }
        }
        if (software.getPackageName() != null) {
            if (!software.getPackageName().equals(versionRecordDTO.getPackageName())) {
                throw new BussinessException(BizExceptionEnum.PACKAGE_FOR_DIFFERENT_APPLICATION);
            }
        }
    }


    @Override
    public void checkUpdate(VersionRecordDTO versionRecordDTO) {

    }
}
