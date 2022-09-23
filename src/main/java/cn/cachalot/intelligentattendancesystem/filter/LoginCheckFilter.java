package cn.cachalot.intelligentattendancesystem.filter;

import cn.cachalot.intelligentattendancesystem.common.BaseContext;
import cn.cachalot.intelligentattendancesystem.common.R;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//响应拦截器
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器  支持通配符*
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    private static final String[] whiteList = new String[]{"/login", "/user/login"};

    //判断有无登陆
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        if (checkWhiteList(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getSession().getAttribute("User") != null) {
            Long userID = (Long) request.getSession().getAttribute("User");
            BaseContext.setId(userID);
            filterChain.doFilter(request, response);
            return;
        }
        //跳转到登陆界面
        response.sendRedirect("/login");
//        response.getWriter().write(JSON.toJSONString(R.error("NOT LOGIN!")));
    }

    private boolean checkWhiteList(String requestURI) {
        for (String path : whiteList) {
            if (PATH_MATCHER.match(path, requestURI)) {
                return true;
            }
        }
        return false;
    }
}

