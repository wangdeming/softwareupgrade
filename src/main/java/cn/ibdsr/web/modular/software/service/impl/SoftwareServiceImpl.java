package cn.ibdsr.web.modular.software.service.impl;

import cn.ibdsr.core.base.service.impl.AbstractBaseService;
import cn.ibdsr.core.base.tips.ResultDTO;
import cn.ibdsr.web.common.constant.state.IsDeleted;
import cn.ibdsr.web.common.exception.BizExceptionEnum;
import cn.ibdsr.web.common.exception.BussinessException;
import cn.ibdsr.web.common.persistence.dao.ProjectMapper;
import cn.ibdsr.web.common.persistence.dao.SoftwareMapper;
import cn.ibdsr.web.common.persistence.model.Project;
import cn.ibdsr.web.common.persistence.model.Software;
import cn.ibdsr.web.core.shiro.ShiroKit;
import cn.ibdsr.web.modular.common.service.ICommonService;
import cn.ibdsr.web.modular.software.dao.SoftwareDao;
import cn.ibdsr.web.modular.software.service.ISoftwareService;
import cn.ibdsr.web.modular.software.transfer.SoftwareDTO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 软件管理Service
 * @Version V1.0
 * @CreateDate 2019/7/11 10:35
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Piht            类说明
 */
@Service
public class SoftwareServiceImpl extends AbstractBaseService<SoftwareDTO, Software> implements ISoftwareService {


    @Resource
    private SoftwareMapper softwareMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private SoftwareDao softwareDao;

    @Override
    public BaseMapper<Software> getMapper() {
        return softwareMapper;
    }

    @Override
    public Software getConversionDO() {
        return new Software();
    }

    @Override
    public SoftwareDTO getConversionDTO() {
        return new SoftwareDTO();
    }

    /**
     *获取项目名称
     */
    @Override
    public List<Object> getProjectName(){
        List<Object> projectNameMap = softwareDao.getProjectName();
        return projectNameMap;
    }

    /**
     *获取软件列表
     */
    @Override
    public List<Map<String,Object>> list(Long projectId) {
        List<Map<String,Object>> resultList = softwareDao.list(projectId);
        return resultList;
    }

    /**
     * 新增软件
     */
    @Override
    public void checkInsert(SoftwareDTO softwareDTO) {

        /**新增软件前先把原有软件的排序全部后移一位*/
        List<Software> softwareList = softwareMapper.selectList(new EntityWrapper<Software>()
                .eq("is_deleted", IsDeleted.NORMAL.getCode()));
        for (Software software : softwareList){
            software.setSequence(software.getSequence() +1);
            softwareMapper.updateById(software);
        }
        /**新增软件放在最前面*/
        softwareDTO.setSequence(1);
        softwareDTO.setCreatedTime(new Date());
        softwareDTO.setModifiedTime(new Date());
        softwareDTO.setCreatedUser(ShiroKit.getUser().getId());
        softwareDTO.setModifiedUser(ShiroKit.getUser().getId());
        softwareDTO.setIsDeleted(IsDeleted.NORMAL.getCode());
    }

    /** 新增软件后，需更新项目表中相应的software_num*/
    @Override
    @Transactional
    public ResultDTO<Long> insert(SoftwareDTO softwareDTO) {
        ResultDTO<Long> resultDTO = super.insert(softwareDTO);

        /**根据software的ProjectId查询对应的project*/
        Project project  = projectMapper.selectById(softwareDTO.getProjectId());
        if (project == null) {
            throw new BussinessException(BizExceptionEnum.PROJECT_IS_NULL);
        }

        /**项目中包含软件数量+1*/
        project.setSoftwareNum(project.getSoftwareNum() + 1);

        projectMapper.updateById(project);

        return resultDTO;
    }

    /**
     *逻辑删除
     * @param id 软件主键id
     */
    @Override
    public void logDelete(Long id) {
        Software software = softwareMapper.selectById(id);
        /**更新对应project表中包含软件数量*/
        Project project  = projectMapper.selectById(software.getProjectId());
        if (project == null) {
            throw new BussinessException(BizExceptionEnum.PROJECT_IS_NULL);
        }
        /**项目中包含软件数量-1*/
        project.setSoftwareNum(project.getSoftwareNum() - 1);
        projectMapper.updateById(project);

        software.setId(id);
        software.setIsDeleted(IsDeleted.DELETED.getCode());
        software.setModifiedTime(new Date());
        software.setModifiedUser(ShiroKit.getUser().getId());
        softwareMapper.updateById(software);
    }

    /**
     *修改软件
     */
    @Override
    public void checkUpdate(SoftwareDTO softwareDTO) {
        softwareDTO.setModifiedTime(new Date());
        softwareDTO.setModifiedUser(ShiroKit.getUser().getId());
    }
}
