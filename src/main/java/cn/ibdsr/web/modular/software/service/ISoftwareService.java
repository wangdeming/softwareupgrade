package cn.ibdsr.web.modular.software.service;

import cn.ibdsr.core.base.service.BaseService;
import cn.ibdsr.web.common.persistence.model.Software;
import cn.ibdsr.web.modular.software.transfer.SoftwareDTO;

import java.util.List;
import java.util.Map;

/**
 * @Description 软件管理Service
 * @Version V1.0
 * @CreateDate 2019/7/11 10:35
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Piht            类说明
 */
public interface ISoftwareService extends BaseService<SoftwareDTO,Software>{

    String TABLE_NAME = "software";

    /**
     *获取项目名称
     * @return
     */
    List<Object> getProjectName();

    /**
     * 获取软件列表
     * @param projectId 项目ID
     * @return
     */
    List<Map<String,Object>> list(Long projectId);

    /**
     * 逻辑删除软件
     * @param id
     */
    void logDelete(Long id);


}
