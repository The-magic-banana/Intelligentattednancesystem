package cn.cachalot.intelligentattendancesystem.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Attend implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long attendId;
    private Date firstTime;
    private Date secondTime;
    private Date thirdTime;
    private Date fourthTime;
    private String firstPlace;
    private String secondPlace;
    private String thirdPlace;
    private String fourthPlace;
    private Integer firstWay;
    private Integer secondWay;
    private Integer thirdWay;
    private Integer fourthWay;
    private String firstData;
    private String secondData;
    private String thirdData;
    private String fourthData;
    private Date updateTime;
    private Long updateUserId;
    private String updateDescribe;
}
