package cn.cachalot.intelligentattendancesystem.service.impl;

import cn.cachalot.intelligentattendancesystem.common.BaseContext;
import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.common.TokenUtil;
import cn.cachalot.intelligentattendancesystem.dto.userDto.LoginPara;
import cn.cachalot.intelligentattendancesystem.dto.userDto.LoginRes;
import cn.cachalot.intelligentattendancesystem.entity.User;
import cn.cachalot.intelligentattendancesystem.mapper.UserMapper;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    UserService userService;

    @Override
    public User getUserById(Long id) {
        User user = userMapper.getUserById(id);
        Integer level = BaseContext.getUser().getLevel();
        String department = BaseContext.getUser().getDepartment();
        if (Objects.equals(id, BaseContext.getUser().getUserId())) {
            return user;
        }
        if (level.equals(1) && Objects.equals(department, user.getDepartment())) {
            return user;
        }
        if (level.equals(0)) {
            return user;
        }
        return null;
    }

    @Override
    public User getLocalUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User selectOneByUsername(String userName) {
        return userMapper.selectOneByUsername(userName);
    }

    @Override
    public R<String> addEmployee(User user) {
        if (userMapper.selectOneByUsername(user.getUserName()) != null) {
            return R.error("用户名已存在!");
        }
        Integer level = BaseContext.getUser().getLevel();
        String department = BaseContext.getUser().getDepartment();
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
        log.info("添加员工:{}", user);
        userMapper.addEmployee(user);
        return R.success("添加成功");
    }

    @Override
    public List<Long> getAllUserId() {
        return userMapper.getAllUserId();
    }

    @Override
    public R<String> updateOne(User user) {
        if (userMapper.selectOneByUsername(user.getUserName()) != null && !Objects.equals(user.getUserId(),
                BaseContext.getUser().getUserId())) {
            return R.error("用户名已存在!");
        }
        if (Objects.equals(user.getUserId(), BaseContext.getUser().getUserId())) {
            userMapper.updateOne(user);
            return R.success("修改成功");
        }
        Integer level = BaseContext.getUser().getLevel();
        String department = BaseContext.getUser().getDepartment();
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
    public List<User> getManagedUserInfo(Integer pageNum, Integer pageSize) {
        User user = BaseContext.getUser();
        Integer level = user.getLevel();
        PageHelper.startPage(pageNum, pageSize);
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

    @Override
    public List<Long> getManagedUserId() {
        Integer level = BaseContext.getUser().getLevel();
        if (level.equals(0)) {
            return userMapper.getAllUserId();
        } else if (level.equals(1)) {
            return userMapper.getUserIdByDepartment(BaseContext.getUser().getDepartment());
        } else {
            List<Long> list = new ArrayList<>();
            list.add(BaseContext.getUser().getUserId());
            return list;
        }
    }

    @Override
    public R<LoginRes> login(LoginPara loginPara) {
        User user = this.selectOneByUsername(loginPara.getUserName());
        if (user == null) {
            return R.error("用户名或密码错误!");
        }
        String password = loginPara.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!user.getPassword().equals(password)) {
            return R.error("用户名或密码错误!");
        }
        String token = TokenUtil.creatToken(user);
        LoginRes loginRes = new LoginRes();
        loginRes.setUser(user);
        loginRes.setToken(token);
        return R.success(loginRes);
    }

    @Override
    public List<User> getManagedUserInfoByUserNameOrId(Integer pageNum, Integer pageSize,
                                                       String userNameOrId) {
        User user = BaseContext.getUser();
        Integer level = user.getLevel();
        PageHelper.startPage(pageNum, pageSize);
        if (level.equals(0)) {
            return userMapper.getAllUserByUserNameOrId(userNameOrId);
        } else if (level.equals(1)) {
            return userMapper.getUserByUserNameOrIdInDepartment(user.getDepartment(), userNameOrId);
        } else {
            List<User> list = new ArrayList<>();
            list.add(user);
            return list;
        }
    }

    @Override
    public Integer getUserLevel(Long id) {
        return userMapper.selectLevelByUserId(id);
    }

    @Override
    public String getDepartmentById(Long id) {
        return userMapper.selectDepartmentByUserId(id);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }
}
