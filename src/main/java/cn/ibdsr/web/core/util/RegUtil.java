package cn.ibdsr.web.core.util;

import cn.ibdsr.core.check.CheckUtil;

/**
 * @Description 类功能和用法的说明
 * @Version V1.0
 * @CreateDate 2019/8/20 17:58
 * <p>
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/8/20      wangzhipeng            类说明
 */
public class RegUtil extends CheckUtil {
    /**
     * 项目编号
     */
    public static final String PROJECT_CODE = "^[A-Z0-9]+-(2[0-9]{3}(0[1-9]|1[0-2]))(-[A-Za-z]+)?$";

    /**
     * 软件代号
     */
    public static final String SOFTWARE_CODE = "^[A-Za-z0-9-]+-(exe|apk|ipa)$";
}
