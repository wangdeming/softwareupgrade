package cn.ibdsr.web.modular.software.controller;

import cn.ibdsr.core.base.controller.BaseController;
import cn.ibdsr.core.base.tips.SuccessDataTip;
import cn.ibdsr.web.common.constant.state.MoveDirection;
import cn.ibdsr.web.modular.common.service.ICommonService;
import cn.ibdsr.web.modular.software.service.ISoftwareService;
import cn.ibdsr.web.modular.software.transfer.SoftwareDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 软件管理控制器
 * @Version V1.0
 * @CreateDate 2019/7/11 10:35
 * <p>
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Piht            类说明
 */
@Controller
@RequestMapping("/software")
public class SoftwareController extends BaseController {

    @Resource
    private ISoftwareService softwareService;

    @Resource
    private ICommonService commonService;

    private String PREFIX = "/software/software/";

    /**
     * 跳转到软件管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "software.html";
    }

    /**
     * 跳转到添加软件管理
     */
    @RequestMapping("/software_add")
    public String softwareAdd() {
        return PREFIX + "software_add.html";
    }

    /**
     * 跳转到修改软件管理
     */
    @RequestMapping("/software_update")
    public String softwareUpdate() {
        return PREFIX + "software_edit.html";
    }

    /**
     *获取项目名称
     */
    @RequestMapping(value = "/getProjectName")
    @ResponseBody
    public Object getProjectName( ) {
        List<Object> result = softwareService.getProjectName();
        return new SuccessDataTip(result);
    }

    /**
     * 获取软件管理列表
     * @param projectId 项目ID
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false)Long projectId) {
        Map<String, Object> map = new HashMap<>(16);
        List<Map<String,Object>> result = softwareService.list(projectId);
        map.put("rows",result);
        Integer total = result == null ? 0 : result.size();
        map.put("total",total);
        return map;
    }

    /**
     * 新增软件管理
     *
     * *@param softwareDTO 软件信息
     *                   projectId 项目ID
     *                   code 软件代号
     *                   name 软件名称
     *                   type 软件类型
     *                   description 说明
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SoftwareDTO softwareDTO) {
        return softwareService.insert(softwareDTO);
    }

    /**
     * 删除软件管理
     * @param  id 软件ID
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
        softwareService.logDelete(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改软件管理
     *
     *@param softwareDTO 软件信息
     *                   id 软件ID
     *                   projectId 项目ID
     *                   code 软件代号
     *                   name 软件名称
     *                   type 软件类型
     *                   description 说明
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SoftwareDTO softwareDTO) {
        return softwareService.updateById(softwareDTO);
    }

    /**
     * 上移操作
     * @param  id 软件ID
     */
    @RequestMapping(value = "/movePre")
    @ResponseBody
    public Object movePre(@RequestParam Long id) {
        commonService.move(ISoftwareService.TABLE_NAME, id, MoveDirection.PREVIOUS.getVal());
        return SUCCESS_TIP;
    }

    /**
     * 下移操作
     * @param  id 软件ID
     */
    @RequestMapping(value = "/moveNext")
    @ResponseBody
    public Object moveNext(@RequestParam Long id) {
        commonService.move(ISoftwareService.TABLE_NAME, id, MoveDirection.NEXT.getVal());
        return SUCCESS_TIP;
    }

}
