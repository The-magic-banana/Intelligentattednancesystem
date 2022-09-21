package cn.cachalot.intelligentattendancesystem.common;

/**
 * 自定义异常类
 */
public class CustomException extends RuntimeException {
    private Integer code;

//    public CustomException(Integer code, String message) {
//        super(message);
//        this.code = code;
//    }

    public CustomException(String message) {
        super(message);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
