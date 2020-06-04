package cn.ibdsr.web.modular.project.controller;

import cn.ibdsr.core.base.controller.BaseController;
import cn.ibdsr.web.common.constant.state.MoveDirection;
import cn.ibdsr.web.modular.common.service.ICommonService;
import cn.ibdsr.web.modular.project.service.IProjectService;
import cn.ibdsr.web.modular.project.transfer.ProjectDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 项目列表控制器
 * @Version V1.0
 * @CreateDate 2019/7/11 10:32
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Piht            类说明
 */
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {

    @Resource
    private IProjectService projectService;

    @Resource
    private ICommonService commonService;

    private String PREFIX = "/project/project/";

    /**
     * 跳转到项目列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "project.html";
    }

    /**
     * 跳转到添加项目列表
     */
    @RequestMapping("/project_add")
    public String projectAdd() {
        return PREFIX + "project_add.html";
    }

    /**
     * 跳转到修改项目列表
     */
    @RequestMapping("/project_update")
    public String projectUpdate() {
        return PREFIX + "project_edit.html";
    }

    /**
     * 获取项目列表
     * @param keyword 搜索关键字
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String keyword) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String,Object>> result = projectService.list(keyword);
        map.put("rows",result);
        Integer total = result == null ? 0 : result.size();
        map.put("total",total);
        return map;
    }

    /**
     * 新增项目列表
     *
     * @param projectDTO 项目信息
     *                   code 项目代号
     *                   name 名称
     *                   intro 简介
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ProjectDTO projectDTO) {
        return projectService.insert(projectDTO);
    }

    /**
     * 删除项目列表
     * @param id 项目ID
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long id) {
        projectService.logDelete(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改项目列表
     * @param projectDTO 项目信息
     *                   id   项目ID
     *                   code 项目代号
     *                   name 名称
     *                   intro 简介
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ProjectDTO projectDTO) {
        return projectService.updateById(projectDTO);
    }

    /**
     * 上移操作
     * @param id 项目id
     */
    @RequestMapping(value = "/movePre")
    @ResponseBody
    public Object movePre(@RequestParam Long id) {
        commonService.move(IProjectService.TABLE_NAME, id, MoveDirection.PREVIOUS.getVal());

        return SUCCESS_TIP;
    }

    /**
     * 下移操作
     * @param id 项目id
     */
    @RequestMapping(value = "/moveNext")
    @ResponseBody
    public Object moveNext(@RequestParam Long id) {
        commonService.move(IProjectService.TABLE_NAME, id, MoveDirection.NEXT.getVal());
        return SUCCESS_TIP;
    }

}
