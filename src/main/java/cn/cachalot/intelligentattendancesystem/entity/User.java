package cn.cachalot.intelligentattendancesystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String userName;
    private String password;
    private Integer role;
    private String department;
    private Integer sex;
    private Date birthday;
    private String phone;
    private String address;
    private String faceData;
    private String fingerData;
    private String describe;
}
