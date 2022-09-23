package cn.cachalot.intelligentattendancesystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */

    private Long userId;
    /**
     * 登陆用户名
     */

    private String userName;
    /**
     * 密码
     */

    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 所属部门
     */
    private String department;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 电话
     */
    private String phone;
    /**
     * 住址
     */
    private String address;
    /**
     * 面部数据地址
     */
    private String faceData;
    /**
     * 指纹数据地址
     */
    private String fingerData;
    /**
     * 备注
     */
    private String describe;
}
