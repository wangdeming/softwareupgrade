package cn.ibdsr.web.common.constant.state;

/**
 * @Description 软件类型
 * @Version V1.0
 * @CreateDate 2019/4/26 13:43
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/4/26      Wujiayun            类说明
 */
public enum SoftwareType {
    IPA(1, ".ipa"), APK(2, ".apk"), EXE(3, ".zip");

    int code;
    String message;

    SoftwareType(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        } else {
            for (SoftwareType ms : SoftwareType.values()) {
                if (ms.getCode() == value) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }

}
