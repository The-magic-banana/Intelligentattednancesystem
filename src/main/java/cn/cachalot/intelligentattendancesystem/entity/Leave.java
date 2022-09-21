package cn.cachalot.intelligentattendancesystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Leave implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long leaveId;
    private Long applicantUserId;
    private Date submitTime;
    private java.sql.Date startTime;
    private java.sql.Date endTime;
    private String reason;
    private Long managerUserId;
    private Date examineTime;
    private Integer status;
}
