package cn.cachalot.intelligentattendancesystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Leave implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 请假记录ID
     */
    private Long leaveId;
    /**
     * 申请请假人ID
     */
    private Long applicantUserId;
    /**
     * 申请时间
     */
    private Date submitTime;
    /**
     * 请假开始时间
     */
    private java.sql.Date startTime;
    /**
     * 请假结束时间
     */
    private java.sql.Date endTime;
    /**
     * 请假原因
     */
    private String reason;
    /**
     * 请假审批人ID
     */
    private Long managerUserId;
    /**
     * 审批时间
     */
    private Date examineTime;
    /**
     * 审批状态
     * 1:待审核
     * 2:审核通过
     * 3:审核不通过
     */
    private Integer status;
}
