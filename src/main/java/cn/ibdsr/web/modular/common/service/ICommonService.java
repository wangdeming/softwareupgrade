package cn.ibdsr.web.modular.common.service;

/**
 * @Description
 * @Version V1.0
 * @CreateDate 2019/7/12 17:47
 * .
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/12       Piht            类说明
 */
public interface ICommonService {

    /**
     * 移动
     * @param id
     * @param moveDirection 移动方向
     */
    void move(String tableName, Long id, Integer moveDirection);

}