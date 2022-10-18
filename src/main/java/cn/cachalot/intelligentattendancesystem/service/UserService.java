package cn.cachalot.intelligentattendancesystem.service;

import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.dto.userDto.LoginPara;
import cn.cachalot.intelligentattendancesystem.dto.userDto.LoginRes;
import cn.cachalot.intelligentattendancesystem.entity.User;


import java.util.List;

public interface UserService {
    User selectOneByUsername(String userName);

    R<String> addEmployee(User user);

    List<Long> getAllUserId();

    R<String> updateOne(User user);

    List<User> getManagedUserInfo(Integer pageNum, Integer pageSize, Long userId);

    List<Long> getManagedUserId(Long userId);

    R<LoginRes> login(LoginPara loginPara);

    List<User> getManagedUserInfoByUserNameOrId(Integer pageNum, Integer pageSize, Long id, String userNameOrId);

    Integer getUserLevel(Long id);

    String getDepartmentById(Long id);
}
