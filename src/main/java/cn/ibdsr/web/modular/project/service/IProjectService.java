package cn.ibdsr.web.modular.project.service;

import cn.ibdsr.core.base.service.BaseService;
import cn.ibdsr.web.common.persistence.model.Project;
import cn.ibdsr.web.modular.project.transfer.ProjectDTO;

import java.util.List;
import java.util.Map;

/**
 * @Description 项目列表Service
 * @Version V1.0
 * @CreateDate 2019/7/11 10:32
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Piht            类说明
 */
public interface IProjectService extends BaseService<ProjectDTO, Project> {

    String TABLE_NAME = "project";

    /**
     * 获取项目列表
     * @param keyword 关键词
     * @return
     */
    List<Map<String,Object>> list(String keyword);

    /**
     * 逻辑删除项目
     * @param id
     */
    void logDelete(Long id);


}
