package cn.ibdsr.web.modular.versionrecord.service;

import cn.ibdsr.core.base.service.BaseService;
import cn.ibdsr.web.common.persistence.model.UploadVersionRecord;
import cn.ibdsr.web.modular.versionrecord.transfer.VersionRecordDTO;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description 版本记录Service
 * @Version V1.0
 * @CreateDate 2019/7/11 10:31
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Wujiayun            类说明
 */
public interface IVersionRecordService extends BaseService<VersionRecordDTO, UploadVersionRecord> {

    /**
     * 分页获取版本记录列表
     * @param softwareId
     * @return
     */
    Page<JSONObject> list(Long softwareId);

    /**
     * 解析上传安装包
     * @param file 文件
     * @param softwareId 软件Id
     * @return
     */
    Map<String, Object> analyse(MultipartFile file, Long softwareId);

    /**
     * 下载
     * @param id
     * @return
     */
    void download(Long id, HttpServletResponse response);

}
