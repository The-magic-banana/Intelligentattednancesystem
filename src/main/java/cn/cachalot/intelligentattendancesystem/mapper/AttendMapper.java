package cn.cachalot.intelligentattendancesystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface AttendMapper {
    void creatUserAttend(@Param("date") Date date, @Param("mapList") List<Map> mapList);

    void creatAttend(@Param("attendIds") List<Long> attendIds);
}
