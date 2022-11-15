package cn.cachalot.intelligentattendancesystem.service;

import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.dto.attendDto.GetAttendRes;
import cn.cachalot.intelligentattendancesystem.entity.Attend;
import cn.cachalot.intelligentattendancesystem.entity.UserAttend;


import java.time.LocalDate;
import java.util.List;

public interface AttendService {
    void creatAttend();

    R<String> sign(Integer type, Long userId, Integer way, String place, String data);

    void checkFirstSign();

//    void checkSecondSign();

    void checkThirdSign();

//    void checkFourthSign();

    List<GetAttendRes> getAttendByUserId(Integer pageNum, Integer pageSize, Long userId, Integer days);

    List<GetAttendRes> getAttendByDate(Integer pageNum, Integer pageSize, LocalDate date);

    UserAttend getAttendByUserIdAndDate(Long userId, LocalDate date);

    R<Attend> getAttendDetail(Long attendId);


}
