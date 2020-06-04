package cn.ibdsr.web.modular.common.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Version V1.0
 * @CreateDate 2019/7/12 17:44
 * .
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/12       Piht            类说明
 */
public interface CommonDao {
    /**
     * 获取前一个元素
     * @param tableName 表名
     * @param sequence 顺序
     * @return
     */
    JSONObject previous(@Param("tableName") String tableName, @Param("sequence") Integer sequence);

    /**
     * 获取后一个元素
     * @param tableName 表名
     * @param sequence 顺序
     * @return
     */
    JSONObject next(@Param("tableName") String tableName, @Param("sequence") Integer sequence);

    /**
     * 根据id获取实例
     * @param tableName
     * @param id
     * @return
     */
    JSONObject selectById(@Param("tableName") String tableName, @Param("id") Long id);

    /**
     * 根据id更新实例
     * @param condition
     */
    void updateById(JSONObject condition);

}
