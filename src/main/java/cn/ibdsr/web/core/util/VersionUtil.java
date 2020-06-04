package cn.ibdsr.web.core.util;

public class VersionUtil {
    /**
     * 将版本号转为纯数字
     * @param versionNo
     * @return
     */
    public static Integer changeVersionNo(String versionNo){
        return Integer.valueOf(versionNo.replace(".",""));
    }
}
