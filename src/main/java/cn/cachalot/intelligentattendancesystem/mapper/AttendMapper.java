package cn.cachalot.intelligentattendancesystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

@Mapper
public interface AttendMapper {
    void creatUserAttend(@Param("userIds") List<Long> userIds, @Param("date") Date date, @Param("attendIds") List<Long> attendIds);

    void creatAttend(@Param("attendIds") List<Long> attendIds);
}
