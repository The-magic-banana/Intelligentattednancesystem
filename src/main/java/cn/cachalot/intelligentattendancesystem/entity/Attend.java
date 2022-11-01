package cn.cachalot.intelligentattendancesystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "考勤记录详细信息")
public class Attend implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 考勤记录id
     */
    @ApiModelProperty("考勤记录id")
    private Long attendId;
    /**
     * 第一次考勤时间
     */
    @ApiModelProperty("第一次考勤时间")
    private LocalDateTime firstTime;
    private LocalDateTime secondTime;
    private LocalDateTime thirdTime;
    private LocalDateTime fourthTime;
    /**
     * 第一次考勤地点
     */
    @ApiModelProperty("第一次考勤地点")
    private String firstPlace;
    private String secondPlace;
    private String thirdPlace;
    private String fourthPlace;
    /**
     * 第一次考勤方式:
     * 1:指纹
     * 2:人脸
     * 3:定位
     */
    @ApiModelProperty("第一次考勤方式1:指纹2:人脸3:定位")
    private Integer firstWay;
    private Integer secondWay;
    private Integer thirdWay;
    private Integer fourthWay;
    /**
     * 第一次考勤数据存储地址
     */
    @ApiModelProperty("第一次考勤数据存储地址")
    private String firstData;
    private String secondData;
    private String thirdData;
    private String fourthData;
    /**
     * 手动更新时间
     */
    @ApiModelProperty("手动更新时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    /**
     * 手动更新操作人
     */
    @ApiModelProperty("手动更新操作人")
    private Long updateUserId;
    /**
     * 更新备注
     */
    @ApiModelProperty("更新备注")
    private String updateDescribe;
}
