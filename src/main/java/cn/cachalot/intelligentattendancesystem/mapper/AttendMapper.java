package cn.cachalot.intelligentattendancesystem.mapper;

import cn.cachalot.intelligentattendancesystem.entity.UserAttend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface AttendMapper {
    void creatUserAttend(@Param("date") Date date, @Param("mapList") List<Map> mapList);

    void creatAttend(@Param("attendIds") List<Long> attendIds);

    void checkFirstSign(@Param("date") Date date);

    void checkSecondSign(@Param("date") Date date);

    void checkThirdSign(@Param("date") Date date);

    void checkFourthSign(@Param("date") Date date);

    List<UserAttend> getAttendByUserId(@Param("userId") Long userId, @Param("days") Integer days);

    List<UserAttend> getAllAttendByDate(@Param("date") Date date);

    List<UserAttend> getAttendByDateAndDepartment(@Param("department") String department, @Param("date") Date date);

    UserAttend getOneAttendByDateAndUserId(@Param("id") Long id, @Param("date") Date date);
}
