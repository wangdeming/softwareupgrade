package cn.ibdsr.web.common.constant.state;

/**
 * @Description 是否强制
 * @Version V1.0
 * @CreateDate 2019/7/11 14:47
 * .
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11      Wujiayun            类说明
 */
public enum IsForce {

    UNFORCE(0, "非强制"), FORCE(1, "强制");

    int code;
    String message;

    IsForce(int code, String message) {
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
            for (IsForce ms : IsForce.values()) {
                if (ms.getCode() == value) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}