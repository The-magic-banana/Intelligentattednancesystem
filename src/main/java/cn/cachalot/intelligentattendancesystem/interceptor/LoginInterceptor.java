package cn.cachalot.intelligentattendancesystem.interceptor;

import cn.cachalot.intelligentattendancesystem.common.BaseContext;
import cn.cachalot.intelligentattendancesystem.common.TokenUtil;
import cn.cachalot.intelligentattendancesystem.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    //controller执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token != null) {
            log.info("token============" + token);
            if (TokenUtil.checkToken(token)) {
                Long userId = Long.valueOf(Objects.requireNonNull(TokenUtil.getUserIdFromToken(token)));
                BaseContext.setUser(userService.getLocalUserById(userId));
                return true;
            }
        }
        response.sendRedirect("/login");
        return false;
    }

    //controller执行中
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle开始");
    }

    //controller执行结束
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        System.out.println("afterCompletion结束");
    }

}
