package cn.cachalot.intelligentattendancesystem.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping
@Api(tags = "页面跳转")
public class PageController {
    /**
     * 主页面
     *
     * @return
     */
    @GetMapping()
    @ApiOperation("跳转到主界面")
    public String index() {
        return "index";
    }

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @GetMapping("/login")
    @ApiOperation("跳转到登陆界面")
    public String login() {
        return "login";
    }
}
