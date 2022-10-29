package cn.cachalot.intelligentattendancesystem.service;

import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.dto.attendDto.GetAttendRes;
import cn.cachalot.intelligentattendancesystem.entity.Attend;
import cn.cachalot.intelligentattendancesystem.entity.UserAttend;

import java.sql.Date;
import java.util.List;

public interface AttendService {
    void creatAttend();

    void checkFirstSign();

    void checkSecondSign();

    void checkThirdSign();

    void checkFourthSign();

    List<GetAttendRes> getAttendByUserId(Integer pageNum, Integer pageSize, Long userId, Integer days);

    List<GetAttendRes> getAttendByDate(Integer pageNum, Integer pageSize, Date date);

    R<Attend> getAttendDetail(Long attendId);
}
