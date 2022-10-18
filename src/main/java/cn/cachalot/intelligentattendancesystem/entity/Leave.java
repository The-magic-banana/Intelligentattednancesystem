package cn.cachalot.intelligentattendancesystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "请假记录信息")
public class Leave implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 请假记录ID
     */
    @ApiModelProperty("请假记录ID")
    private Long leaveId;
    /**
     * 申请请假人ID
     */
    @ApiModelProperty("申请请假人ID")
    private Long applicantUserId;
    /**
     * 申请时间
     */
    @ApiModelProperty("申请时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submitTime;
    /**
     * 请假开始时间
     */
    @ApiModelProperty("请假开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private java.sql.Date startTime;
    /**
     * 请假结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("请假结束时间")
    private java.sql.Date endTime;
    /**
     * 请假原因
     */
    @ApiModelProperty("请假原因")
    private String reason;
    /**
     * 请假审批人ID
     */
    @ApiModelProperty("请假审批人ID")
    private Long managerUserId;
    /**
     * 审批时间
     */
    @ApiModelProperty("审批时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examineTime;
    /**
     * 审批状态
     * 1:待审核
     * 2:审核通过
     * 3:审核不通过
     */
    @ApiModelProperty("审批状态1:待审核2:审核通过3:审核不通过")
    private Integer status;
}
