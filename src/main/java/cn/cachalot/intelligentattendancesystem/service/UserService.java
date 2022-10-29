package cn.cachalot.intelligentattendancesystem.service;

import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.dto.userDto.LoginPara;
import cn.cachalot.intelligentattendancesystem.dto.userDto.LoginRes;
import cn.cachalot.intelligentattendancesystem.entity.User;


import java.util.List;

public interface UserService {
    User getUserById(Long id);

    User getLocalUserById(Long id);

    User selectOneByUsername(String userName);

    R<String> addEmployee(User user);

    List<Long> getAllUserId();

    R<String> updateOne(User user);

    List<User> getManagedUserInfo(Integer pageNum, Integer pageSize);

    List<Long> getManagedUserId();

    R<LoginRes> login(LoginPara loginPara);

    List<User> getManagedUserInfoByUserNameOrId(Integer pageNum, Integer pageSize, String userNameOrId);

    Integer getUserLevel(Long id);

    String getDepartmentById(Long id);
}
