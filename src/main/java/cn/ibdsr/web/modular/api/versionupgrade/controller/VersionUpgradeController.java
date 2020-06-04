package cn.ibdsr.web.modular.api.versionupgrade.controller;

import cn.ibdsr.core.base.controller.BaseController;
import cn.ibdsr.core.base.tips.SuccessDataTip;
import cn.ibdsr.web.modular.api.versionupgrade.service.IVersionUpgradeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description 版本升级控制器
 * @Version V1.0
 * @CreateDate 2019/7/12 11:25
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/12      Wujiayun            类说明
 */
@Controller
@RequestMapping("/api/versionUpgrade")
public class VersionUpgradeController extends BaseController {

    @Resource
    private IVersionUpgradeService versionUpgradeService;

    /**
     * 版本升级
     * @param softwareId
     * @param currentVersionNo
     * @return
     */
    @RequestMapping(value = "/upgrade")
    @ResponseBody
    public Object upgrade(@RequestParam String softwareId, @RequestParam String currentVersionNo) {
        Map<String, Object> result = versionUpgradeService.upgrade(softwareId, currentVersionNo);
        return new SuccessDataTip(result);
    }
}
