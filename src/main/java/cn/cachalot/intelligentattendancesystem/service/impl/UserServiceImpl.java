package cn.cachalot.intelligentattendancesystem.service.impl;

import cn.cachalot.intelligentattendancesystem.entity.User;
import cn.cachalot.intelligentattendancesystem.mapper.UserMapper;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User selectOneByUsername(String userName) {
        return userMapper.selectOneByUsername(userName);
    }

    @Override
    public void addEmployee(User user) {
        user.setUserId(IdWorker.getId());
        userMapper.addEmployee(user);
    }

    @Override
    public void updateOne(User user) {
        userMapper.updateOne(user);
    }

    @Override
    public List<User> getManagedUserInfo(Long userId) {
        User user = userMapper.selectOneByUserId(userId);
        Integer level = user.getLevel();
        if (level.equals(0)) {
            return userMapper.getAllUser();
        } else if (level.equals(1)) {
            return userMapper.getUserByDepartment(user.getDepartment());
        } else {
            List<User> list = new ArrayList<>();
            list.add(user);
            return list;
        }
    }
}
