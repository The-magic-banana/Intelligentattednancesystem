package cn.cachalot.intelligentattendancesystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UserAttend implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date day;
    private Long attendId;
    private Long userId;
    private Integer status;
}
