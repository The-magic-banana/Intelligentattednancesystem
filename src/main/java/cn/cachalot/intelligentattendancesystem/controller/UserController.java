package cn.cachalot.intelligentattendancesystem.controller;


import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.common.SMSUtils;
import cn.cachalot.intelligentattendancesystem.common.TokenUtil;
import cn.cachalot.intelligentattendancesystem.entity.User;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import cn.cachalot.intelligentattendancesystem.dto.userDto.LoginPara;
import cn.cachalot.intelligentattendancesystem.dto.userDto.LoginRes;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理相关接口")
public class UserController {
    @Resource
    UserService userService;

    @ApiOperation("用户登陆")
    @PostMapping("/login")
    @ApiImplicitParam(name = "loginPara", value = "登陆信息", required = true)
    public R<LoginRes> login(HttpServletRequest request, @RequestBody LoginPara loginPara) {
        return userService.login(loginPara);
    }

    /**
     * 添加新打工仔
     *
     * @param request
     * @param user    leval和userName必填  初始密码默认123456 Id后台生成
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加新用户")
    @ApiImplicitParam(name = "user", value = "员工信息", required = true)
    public R<String> save(HttpServletRequest request, @RequestBody User user) {
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        return userService.addEmployee(user);

    }

    /**
     * 修改打工仔信息
     *
     * @param request
     * @param user    不修改的数据留空
     * @return
     */
    @ApiOperation("修改用户数据")
    @PutMapping("/update")
    @ApiImplicitParam(name = "user", value = "修改信息,不改的留空", required = true)
    public R<String> update(HttpServletRequest request, @RequestBody User user) {
        R<String> res = userService.updateOne(user);
        return res;
    }

    /**
     * 分页查找权限内打工仔信息
     *
     * @param pageNum  查第几页
     * @param pageSize 每页多少条
     * @return
     */
    @ApiOperation("查询用户信息")
    @PostMapping("/getManagedUserInfo")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "第几页", required = true), @ApiImplicitParam(name =
            "pageSize", value = "每一页有多少数据", required = true)})
    public R<PageInfo<User>> getUserInfo(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        List<User> list = userService.getManagedUserInfo(pageNum, pageSize);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return R.success(pageInfo);
    }

    @ApiOperation("模糊查询用户名或Id")
    @PostMapping("/getManagedUserInfoByUserNameOrId")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "第几页", required = true), @ApiImplicitParam(name =
            "pageSize", value = "每一页有多少数据", required = true), @ApiImplicitParam(name = "userNameOrId", value =
            "用户名或Id", required = true)})
    public R<PageInfo<User>> getManagedUserInfoByUserNameOrId(@RequestParam Integer pageNum,
                                                              @RequestParam Integer pageSize,
                                                              @RequestParam String userNameOrId) {
        List<User> list = userService.getManagedUserInfoByUserNameOrId(pageNum, pageSize, userNameOrId);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return R.success(pageInfo);
    }

    @ApiOperation("发送短信验证码")
    @PostMapping("/getSMS")
    @ApiImplicitParam(name = "phone", value = "手机号码", required = true)
    public R<String> sendMsg(String phone, HttpSession session) {
        if (phone == null || phone.equals("")) {
            return R.error("手机号码不能为空!");
        }
        User user = userService.getUserByPhone(phone);
        if (user == null) {
            return R.error("该手机号码未注册!");
        }
        Integer codeNum = new Random().nextInt(999999);//生成随机数，最大为999999
        if (codeNum < 100000) {
            codeNum = codeNum + 100000;//保证随机数为6位数字
        }
        String code = codeNum.toString();
        log.info("手机号为：" + phone);
        log.info("验证码为：" + code);
        SMSUtils.sendMessage("阿里云短信测试", "SMS_154950909", phone, code);
        session.setAttribute(phone, code);
        return R.success("短信发送成功");
    }

    @ApiOperation("发送短信验证码(测试用,直接返回验证码)")
    @PostMapping("/getSMStest")
    @ApiImplicitParam(name = "phone", value = "手机号码", required = true)
    public R<String> sendMsgtest(String phone, HttpSession session) {
        if (phone == null || phone.equals("")) {
            return R.error("手机号码不能为空!");
        }
        User user = userService.getUserByPhone(phone);
        if (user == null) {
            return R.error("该手机号码未注册!");
        }
        Integer codeNum = new Random().nextInt(999999);//生成随机数，最大为999999
        if (codeNum < 100000) {
            codeNum = codeNum + 100000;//保证随机数为6位数字
        }
        String code = codeNum.toString();
        log.info("手机号为：" + phone);
        log.info("验证码为：" + code);
//        SMSUtils.sendMessage("阿里云短信测试", "SMS_154950909", phone, code);
        session.setAttribute(phone, code);
        return R.success(code);
    }

    @ApiOperation("手机验证码登陆")
    @PostMapping("/SMSlogin")
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "手机号码", required = true), @ApiImplicitParam(name =
            "code", value = "验证码", required = true)})
    public R<LoginRes> SMSlogin(String phone, String code, HttpSession session) {
        String codeInSession = session.getAttribute(phone).toString();
        if (codeInSession != null && codeInSession.equals(code)) {
            User user = userService.getUserByPhone(phone);
            String token = TokenUtil.creatToken(user);
            LoginRes loginRes = new LoginRes();
            loginRes.setUser(user);
            loginRes.setToken(token);
            return R.success(loginRes);
        }
        return R.error("登录失败!");
    }
}
