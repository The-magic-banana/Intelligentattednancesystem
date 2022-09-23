package cn.cachalot.intelligentattendancesystem.service;

import cn.cachalot.intelligentattendancesystem.entity.User;

import java.util.List;

public interface UserService {
    User selectOneByUsername(String userName);

    void addEmployee(User user);

    void updateOne(User user);

    List<User> getManagedUserInfo(Long userId);
}
