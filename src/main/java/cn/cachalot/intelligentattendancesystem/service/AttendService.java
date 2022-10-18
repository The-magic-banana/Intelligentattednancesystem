package cn.cachalot.intelligentattendancesystem.service;

import cn.cachalot.intelligentattendancesystem.entity.UserAttend;

import java.sql.Date;
import java.util.List;

public interface AttendService {
    void creatAttend();

    void checkFirstSign();

    void checkSecondSign();

    void checkThirdSign();

    void checkFourthSign();

    List<UserAttend> getAttendByUserId(Integer pageNum, Integer pageSize, Long userId, Integer days);

    List<UserAttend> getAttendByDate(Integer pageNum, Integer pageSize, Long id, Date date);
}
