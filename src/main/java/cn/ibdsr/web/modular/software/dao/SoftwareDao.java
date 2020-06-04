package cn.ibdsr.web.modular.software.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description 软件管理Dao
 * @Version V1.0
 * @CreateDate 2019/7/11 10:35
 * <p>
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Piht            类说明
 */
public interface SoftwareDao {

    /**
     *获取项目名称
     *  @return
     */
    List<Object> getProjectName();

    /**
     *获取软件管理列表
     * @param projectId
     * @return
     */
    List<Map<String,Object>> list(@Param("projectId")Long projectId);


}
