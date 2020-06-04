package cn.ibdsr.web.modular.project.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description 项目列表Dao
 * @Version V1.0
 * @CreateDate 2019/7/11 10:32
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Piht            类说明
 */
public interface ProjectDao {

    /**
     * 获取项目列表
     * @param keyword
     * @return
     */
    List<Map<String,Object>> list(@Param("keyword") String keyword);

}
