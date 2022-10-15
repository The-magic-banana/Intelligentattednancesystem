package cn.cachalot.intelligentattendancesystem.service;

import cn.cachalot.intelligentattendancesystem.entity.UserAttend;

import java.util.List;

public interface AttendService {
    void creatAttend();

    void checkFirstSign();

    void checkSecondSign();

    void checkThirdSign();

    void checkFourthSign();

    List<UserAttend> getSingleAttend(Long userId, Integer days);
}
