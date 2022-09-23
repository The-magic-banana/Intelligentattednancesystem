package cn.cachalot.intelligentattendancesystem.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping
public class PageController {
    /**
     * 主页面
     *
     * @return
     */
    @GetMapping()
    public String index() {
        return "index";
    }

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
