package cn.ibdsr.web.common.constant.state;

/**
 * @Description 移动方向
 * @Version V1.0
 * @CreateDate 2019/7/11 14:54
 * .
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11       Piht            类说明
 */
public enum MoveDirection {

    PREVIOUS(1, "上移"),
    NEXT(2, "下移"),
    ;

    Integer val;
    String message;

    MoveDirection(Integer val, String message) {
        this.val = val;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return null;
        } else {
            for (MoveDirection val : MoveDirection.values()) {
                if (val.getVal().equals(value)) {
                    return val.getMessage();
                }
            }
            return null;
        }
    }
}
