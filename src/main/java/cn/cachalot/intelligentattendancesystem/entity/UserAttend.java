package cn.cachalot.intelligentattendancesystem.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
@ApiModel("用户考勤简略信息")
public class UserAttend implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 记录ID
     */
    @ApiModelProperty("记录ID")
    private Date day;
    /**
     * 考勤详细信息Id
     */
    @ApiModelProperty("考勤详细信息Id")
    private Long attendId;
    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private Long userId;
    /**
     * 考勤状态
     * 1:正常考勤
     * 2:放假
     * 3:请假
     * 4:缺勤
     * 5:迟到或早退
     * 6:未达到该日期
     */
    @ApiModelProperty("考勤状态1:正常考勤\n" + "2:放假\n" + "3:请假\n" + "4:缺勤\n" + "5:迟到或早退\n" + "6:未达到该日期")
    private Integer status;
}
