package cn.cachalot.intelligentattendancesystem.controller;

import cn.cachalot.intelligentattendancesystem.common.BaseContext;
import cn.cachalot.intelligentattendancesystem.common.R;
import cn.cachalot.intelligentattendancesystem.entity.User;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import cn.cachalot.intelligentattendancesystem.dto.userdto.LoginPara;
import cn.cachalot.intelligentattendancesystem.dto.userdto.LoginRes;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        R<String> res = userService.addEmployee(user);
        return res;
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
    @ApiOperation("分页查询有权限管理的用户信息")
    @PostMapping("/getManagedUserInfo")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "第几页", required = true), @ApiImplicitParam(name = "pageSize", value = "每一页有多少数据", required = true)})
    public R<PageInfo<User>> getUserInfo(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.getManagedUserInfo(BaseContext.getId());
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return R.success(pageInfo);
    }
}
