package cn.ibdsr.web.modular.api.versionupgrade.service.impl;

import cn.ibdsr.core.util.ToolUtil;
import cn.ibdsr.web.common.constant.state.IsDeleted;
import cn.ibdsr.web.common.constant.state.IsForce;
import cn.ibdsr.web.common.exception.BizExceptionEnum;
import cn.ibdsr.web.common.exception.BussinessException;
import cn.ibdsr.web.common.persistence.dao.SoftwareMapper;
import cn.ibdsr.web.common.persistence.model.Software;
import cn.ibdsr.web.core.util.ImageUtil;
import cn.ibdsr.web.core.util.VersionUtil;
import cn.ibdsr.web.modular.api.versionupgrade.service.IVersionUpgradeService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 版本升级Service
 * @Version V1.0
 * @CreateDate 2019/7/12 11:25
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/12      Wujiayun            类说明
 */
@Service
public class VersionUpgradeServiceImpl implements IVersionUpgradeService {

    @Resource
    private SoftwareMapper softwareMapper;

    @Override
    public Map<String,Object> upgrade(String softwareId, String currentVersionNo){
        Software software = checkSoftwareId(softwareId);
        if (ToolUtil.isEmpty(currentVersionNo)) {
            throw new BussinessException(BizExceptionEnum.CURRENT_VERSION_NO_IS_NULL);
        }
        Map<String, Object> result = new HashMap<>();
        if (VersionUtil.changeVersionNo(currentVersionNo) >= VersionUtil.changeVersionNo(software.getVersionNo())){
            result.put("isUpdate", false);
        }else{
            result.put("isUpdate", true);
            result.put("isForce", software.getIsForce() == IsForce.FORCE.getCode());
            result.put("latestVersionNo", software.getVersionNo());
            result.put("url", ImageUtil.setImageURL(software.getUrl()));
        }
        return result;
    }

    /**
     * 校验软件ID，并获取软件信息
     *
     * @param softwareId 软件ID
     * @return
     */
    private Software checkSoftwareId(String softwareId) {
        if (ToolUtil.isEmpty(softwareId)) {
            throw new BussinessException(BizExceptionEnum.SOFTWARE_ID_IS_NULL);
        }
        List<Software> softwareList = softwareMapper.selectList(
                new EntityWrapper<Software>()
                        .eq("code", softwareId)
                        .eq("is_deleted", IsDeleted.NORMAL.getCode())
        );
        if (ToolUtil.isEmpty(softwareList)) {
            throw new BussinessException(BizExceptionEnum.SOFTWARE_IS_NOT_EXIST);
        }
        Software software = softwareList.get(0);
        if (ToolUtil.isEmpty(software.getAppName())){
            throw new BussinessException(BizExceptionEnum.NO_PACKAGE);
        }
        return software;
    }
}
