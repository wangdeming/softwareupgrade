package cn.ibdsr.web.modular.common.service.impl;

import cn.ibdsr.core.util.DateUtil;
import cn.ibdsr.core.util.ToolUtil;
import cn.ibdsr.web.common.constant.state.MoveDirection;
import cn.ibdsr.web.common.exception.BizExceptionEnum;
import cn.ibdsr.web.common.exception.BussinessException;
import cn.ibdsr.web.core.shiro.ShiroKit;
import cn.ibdsr.web.modular.common.dao.CommonDao;
import cn.ibdsr.web.modular.common.service.ICommonService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Description
 * @Version V1.0
 * @CreateDate 2019/7/12 17:48
 * .
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/12       Piht            类说明
 */
@Service
public class CommonServiceImpl implements ICommonService {

    @Resource
    private CommonDao commonDao;

    private static final String SEQUENCE = "sequence";

    @Override
    @Transactional
    public void move(String tableName, Long id,  Integer moveDirection) {
        if (ToolUtil.isEmpty(id)) {
            throw new BussinessException(BizExceptionEnum.ID_IS_NULL);
        }
        JSONObject src = commonDao.selectById(tableName, id);
        if (ToolUtil.isEmpty(src)) {
            throw new BussinessException(BizExceptionEnum.OBJECT_NOT_EXIST);
        }

        JSONObject dest;
        Integer sequence = src.getInteger(SEQUENCE);
        if (MoveDirection.PREVIOUS.getVal().equals(moveDirection)) {
            dest = commonDao.previous(tableName, sequence);
            if (dest == null) {
                throw new BussinessException(BizExceptionEnum.PREVIOUS_NOT_EXIST);
            }
        } else {
            dest = commonDao.next(tableName, sequence);
            if (dest == null) {
                throw new BussinessException(BizExceptionEnum.NEXT_NOT_EXIST);
            }
        }
        // 交换序列值
        exchangeSequence(tableName, src, dest);
    }

    /**
     * 交换序列值
     * @param tableName
     * @param src
     * @param dest
     */
    private void exchangeSequence(String tableName, JSONObject src, JSONObject dest) {
        String now = "'" + DateUtil.getTime() + "'"; // 字符串一定要加上单引号，否则报错
        Long userId = ShiroKit.getUser().getId();

        Integer seqTemp = dest.getInteger(SEQUENCE);

        dest.put(SEQUENCE, src.getInteger(SEQUENCE));
        dest.put("modified_time", now);
        dest.put("modified_user", userId);
        dest.put("tableName", tableName);
        commonDao.updateById(dest);

        src.put(SEQUENCE, seqTemp);
        src.put("modified_time", now);
        src.put("modified_user", userId);
        src.put("tableName", tableName);
        commonDao.updateById(src);
    }
}
