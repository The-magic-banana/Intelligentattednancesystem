package cn.cachalot.intelligentattendancesystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Attend implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 考勤记录id
     */
    private Long attendId;
    /**
     * 第一次考勤时间
     */
    private Date firstTime;
    private Date secondTime;
    private Date thirdTime;
    private Date fourthTime;
    /**
     * 第一次考勤地点
     */
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
    private Integer firstWay;
    private Integer secondWay;
    private Integer thirdWay;
    private Integer fourthWay;
    /**
     * 第一次考勤数据存储地址
     */
    private String firstData;
    private String secondData;
    private String thirdData;
    private String fourthData;
    /**
     * 手动更新时间
     */
    private Date updateTime;
    /**
     * 手动更新操作人
     */
    private Long updateUserId;
    /**
     * 更新备注
     */
    private String updateDescribe;
}
