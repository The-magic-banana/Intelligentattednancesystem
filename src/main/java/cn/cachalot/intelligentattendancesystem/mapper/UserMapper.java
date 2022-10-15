package cn.cachalot.intelligentattendancesystem.mapper;

import cn.cachalot.intelligentattendancesystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectOneByUsername(@Param("userName") String userName);

    void addEmployee(@Param("user") User user);

    void updateOne(@Param("user") User user);

    List<User> getAllUser();

    List<User> getUserByDepartment(@Param("department") String department);

    List<Long> getUserIdByDepartment(@Param("department") String department);

    User selectOneByUserId(@Param("userId") Long userId);

    Integer selectLevelByUserId(@Param("userId") Long userId);

    String selectDepartmentByUserId(@Param("userId") Long userId);

    List<Long> getAllUserId();
}
