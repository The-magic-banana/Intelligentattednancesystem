package cn.cachalot.intelligentattendancesystem.service.impl;

import cn.cachalot.intelligentattendancesystem.common.BaseContext;
import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.entity.User;
import cn.cachalot.intelligentattendancesystem.mapper.UserMapper;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public R<String> addEmployee(User user) {
        Integer level = userMapper.selectLevelByUserId(BaseContext.getId());
        String department = userMapper.selectDepartmentByUserId(BaseContext.getId());
        //level数字越小权限越大
        if (user.getLevel() < level) {
            return R.error("不能赋予更高权限!");
        }
        if (level.equals(2)) {
            return R.error("你没有权限添加其他员工!");
        }
        if (level.equals(1) && !Objects.equals(department, user.getDepartment())) {
            return R.error("你没有权限添加其他部门员工!");
        }
        user.setUserId(IdWorker.getId());
        userMapper.addEmployee(user);
        return R.success("添加成功");
    }

    @Override
    public R<String> updateOne(User user) {
        if (Objects.equals(user.getUserId(), BaseContext.getId())) {
            userMapper.updateOne(user);
            return R.success("修改成功");
        }
        Integer level = userMapper.selectLevelByUserId(BaseContext.getId());
        String department = userMapper.selectDepartmentByUserId(BaseContext.getId());
        if (level.equals(1) && !Objects.equals(department, user.getDepartment())) {
            return R.error("您没有权限修改该部门的用户信息!");
        }
        if (level.equals(2)) {
            return R.error("您没有权限修改其他用户信息!");
        }
        userMapper.updateOne(user);
        return R.success("修改成功");
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
