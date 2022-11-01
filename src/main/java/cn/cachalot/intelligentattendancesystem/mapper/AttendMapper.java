package cn.cachalot.intelligentattendancesystem.mapper;

import cn.cachalot.intelligentattendancesystem.dto.attendDto.GetAttendRes;
import cn.cachalot.intelligentattendancesystem.entity.Attend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AttendMapper {
    void sign(@Param("attendId") Long attendId, @Param("status") Integer status);

    void signFirst(@Param("attendId") Long attendId, @Param("DateTime") LocalDateTime dateTime,
                   @Param("place") String place, @Param("way") Integer way, @Param("data") String data);

    void signSecond(@Param("attendId") Long attendId, @Param("DateTime") LocalDateTime dateTime,
                    @Param("place") String place, @Param("way") Integer way, @Param("data") String data);

    void signThird(@Param("attendId") Long attendId, @Param("DateTime") LocalDateTime dateTime,
                   @Param("place") String place, @Param("way") Integer way, @Param("data") String data);

    void signFourth(@Param("attendId") Long attendId, @Param("DateTime") LocalDateTime dateTime,
                    @Param("place") String place, @Param("way") Integer way, @Param("data") String data);

    void creatUserAttend(@Param("date") LocalDate date, @Param("mapList") List<Map> mapList);

    void creatAttend(@Param("attendIds") List<Long> attendIds);

    void checkFirstSign(@Param("date") LocalDate date);

//    void checkSecondSign(@Param("date") LocalDate date);

    void checkThirdSign(@Param("date") LocalDate date);

//    void checkFourthSign(@Param("date") LocalDate date);

    List<GetAttendRes> getAttendByUserId(@Param("userId") Long userId, @Param("days") Integer days);

    List<GetAttendRes> getAllAttendByDate(@Param("date") LocalDate date);

    List<GetAttendRes> getAttendByDateAndDepartment(@Param("department") String department,
                                                    @Param("date") LocalDate date);

    GetAttendRes getOneAttendByDateAndUserId(@Param("id") Long id, @Param("date") LocalDate date);

    Attend getAttendDetail(@Param("attendId") Long attendId);

    Long getUserIdByAttendId(@Param("attendId") Long attendId);
}
