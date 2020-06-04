package cn.ibdsr.web.modular.api.versionupgrade.service;

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
public interface IVersionUpgradeService {

    /**
     * 版本升级
     * @param softwareId
     * @param currentVersionNo
     * @return
     */
    Map<String,Object> upgrade(String softwareId, String currentVersionNo);
}
