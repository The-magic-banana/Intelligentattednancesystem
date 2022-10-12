package cn.cachalot.intelligentattendancesystem.service;

import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.dto.userdto.LoginPara;
import cn.cachalot.intelligentattendancesystem.dto.userdto.LoginRes;
import cn.cachalot.intelligentattendancesystem.entity.User;


import java.util.List;

public interface UserService {
    User selectOneByUsername(String userName);

    R<String> addEmployee(User user);

    R<String> updateOne(User user);

    List<User> getManagedUserInfo(Long userId);

    R<LoginRes> login(LoginPara loginPara);
}
