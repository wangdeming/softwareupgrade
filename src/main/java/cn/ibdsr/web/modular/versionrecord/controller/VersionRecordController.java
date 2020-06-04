package cn.ibdsr.web.modular.versionrecord.controller;

import cn.ibdsr.core.base.controller.BaseController;
import cn.ibdsr.web.modular.versionrecord.service.IVersionRecordService;
import cn.ibdsr.web.modular.versionrecord.transfer.VersionRecordDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 版本记录控制器
 * @Version V1.0
 * @CreateDate 2019/7/11 10:31
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Wujiayun            类说明
 */
@Controller
@RequestMapping("/versionRecord")
public class VersionRecordController extends BaseController {

    private String PREFIX = "/versionrecord/versionrecord/";

    @Resource
    private IVersionRecordService versionRecordService;

    /**
     * 跳转到版本记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "versionRecord.html";
    }

    /**
     * 跳转到上传安装包
     */
    @RequestMapping("/toUploadPackage/{softwareId}")
    public String toUploadPackage(@PathVariable Long softwareId, Model model) {
        model.addAttribute("softwareId", softwareId);
        return PREFIX + "uploadPackage.html";
    }

    /**
     * 分页获取版本记录列表
     * @param softwareId 软件ID
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Long softwareId) {
        return packForBT(versionRecordService.list(softwareId));
    }

    /**
     * 解析上传安装包
     * @param file 文件
     * @param softwareId 软件Id
     * @return
     */
    @RequestMapping(value = "/analyse")
    @ResponseBody
    public Object analyse(@RequestParam MultipartFile file, Long softwareId) {
        return versionRecordService.analyse(file, softwareId);
    }

    /**
     * 新增安装包
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public Object add(VersionRecordDTO versionRecordDTO) {
        return versionRecordService.insert(versionRecordDTO);
    }

    /**
     * 下载
     * @param id
     * @param response
     */
    @RequestMapping(value="/download")
    public void download(@RequestParam Long id, HttpServletResponse response) {
        versionRecordService.download(id, response);
    }
}
