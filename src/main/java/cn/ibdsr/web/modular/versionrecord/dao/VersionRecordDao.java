package cn.ibdsr.web.modular.versionrecord.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 版本记录Dao
 * @Version V1.0
 * @CreateDate 2019/7/11 10:31
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Wujiayun            类说明
 */
public interface VersionRecordDao {


    /**
     * 分页获取版本记录列表
     * @param softwareId
     * @return
     */
    List<JSONObject> list(@Param("page") Page<JSONObject> page, @Param("softwareId")Long softwareId);
}
