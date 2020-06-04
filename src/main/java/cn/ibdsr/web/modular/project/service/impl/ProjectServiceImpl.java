package cn.ibdsr.web.modular.project.service.impl;

import cn.ibdsr.core.base.service.impl.AbstractBaseService;
import cn.ibdsr.web.common.constant.state.IsDeleted;
import cn.ibdsr.web.common.persistence.dao.ProjectMapper;
import cn.ibdsr.web.common.persistence.model.Project;
import cn.ibdsr.web.core.shiro.ShiroKit;
import cn.ibdsr.web.modular.common.service.ICommonService;
import cn.ibdsr.web.modular.project.dao.ProjectDao;
import cn.ibdsr.web.modular.project.service.IProjectService;
import cn.ibdsr.web.modular.project.transfer.ProjectDTO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 项目列表Service
 * @Version V1.0
 * @CreateDate 2019/7/11 10:32
 * <p>
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Piht            类说明
 */
@Service
public class ProjectServiceImpl extends AbstractBaseService<ProjectDTO, Project> implements IProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ProjectDao projectDao;

    @Override
    public BaseMapper<Project> getMapper(){
        return projectMapper;
    }

    @Override
    public Project getConversionDO(){
        return new Project();
    }

    @Override
    public ProjectDTO getConversionDTO(){
        return new ProjectDTO();
    }

    /**
     * 获取项目列表
     * @param keyword 关键词
     * @return
     */
    @Override
    public List<Map<String,Object>> list(String keyword) {
        List<Map<String,Object>> resultList = projectDao.list(keyword);
        return resultList;
    }

    /**
     *新增项目
     */
    @Override
    @Transactional
    public void checkInsert(ProjectDTO projectDTO){

        /**新增项目前，先把原来项目的排序全部后移一位*/
        List<Project> projectList = projectMapper.selectList(new EntityWrapper<Project>()
                .eq("is_deleted", IsDeleted.NORMAL.getCode()));
        for (Project project : projectList){
            project.setSequence(project.getSequence() +1);
            projectMapper.updateById(project);
        }
        /**新增项目置顶*/
        projectDTO.setSequence(1);
        projectDTO.setCreatedTime(new Date());
        projectDTO.setModifiedTime(new Date());
        projectDTO.setCreatedUser(ShiroKit.getUser().getId());
        projectDTO.setModifiedUser(ShiroKit.getUser().getId());
        projectDTO.setIsDeleted(IsDeleted.NORMAL.getCode());

    }

    /**
     *修改项目
     */
    @Override
    public void checkUpdate(ProjectDTO projectDTO) {
        projectDTO.setModifiedTime(new Date());
        projectDTO.setModifiedUser(ShiroKit.getUser().getId());
    }

    /**
     *逻辑删除
     */
    @Override
    public void logDelete(Long id) {
        Project project = new Project();
        project.setId(id);
        project.setIsDeleted(IsDeleted.DELETED.getCode());
        project.setModifiedTime(new Date());
        project.setModifiedUser(ShiroKit.getUser().getId());
        projectMapper.updateById(project);
    }


}
