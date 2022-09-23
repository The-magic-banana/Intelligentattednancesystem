package cn.cachalot.intelligentattendancesystem.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//通用返回结果类
@Data
public class R<T> {

    /**
     * 状态码
     * 0:失败
     * 1:成功
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg; //错误信息
    /**
     * 数据
     */
    private T data; //数据

//    private Map map = new HashMap(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }
}
