package cn.cachalot.intelligentattendancesystem.controller;

import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.entity.User;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 登陆接口
     *
     * @param request
     * @param user    userName 和 password必填
     * @return
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user) {
        log.info(user.toString());
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User res = userService.selectOneByUsername(user.getUserName());
        if (res == null) {
            return R.error("用户名或密码错误!");
        }
        if (!res.getPassword().equals(password)) {
            return R.error("用户名或密码错误!");
        }
        request.getSession().setAttribute("User", res.getUserId());
        return R.success(res);
    }

    /**
     * 登出接口
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("User");
        return R.success("退出成功");
    }

    /**
     * 添加新打工仔
     *
     * @param request
     * @param user    leval和userName必填  初始密码默认123456 Id后台生成
     * @return
     */
    @PostMapping("/add")
    public R<String> save(HttpServletRequest request, @RequestBody User user) {
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        userService.addEmployee(user);
        return R.success("添加成功");
    }

    /**
     * 修改打工仔信息
     *
     * @param request
     * @param user    不修改的数据留空
     * @return
     */
    @PutMapping("/update")
    public R<String> update(HttpServletRequest request, @RequestBody User user) {
        userService.updateOne(user);
        return R.success("修改成功");
    }

    /**
     * 分页查找权限内打工仔信息
     *
     * @param pageNum  查第几页
     * @param pageSize 每页多少条
     * @param user     userId必填
     * @return
     */
    @PostMapping("/getManagedUserInfo")
    public R<PageInfo<User>> getUserInfo(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestBody User user) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.getManagedUserInfo(user.getUserId());
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return R.success(pageInfo);
    }

}
