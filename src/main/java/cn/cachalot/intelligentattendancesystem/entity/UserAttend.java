package cn.cachalot.intelligentattendancesystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UserAttend implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 记录ID
     */
    private Date day;
    /**
     * 考勤详细信息Id
     */
    private Long attendId;
    /**
     * 用户Id
     */
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
    private Integer status;
}
