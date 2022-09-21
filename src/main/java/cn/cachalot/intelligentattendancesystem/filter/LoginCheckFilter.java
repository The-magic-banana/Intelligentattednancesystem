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
    private static final String[] whiteList = new String[]{"/employee/login", "/employee/logout", "/backend/**", "/front/**"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        log.info("拦截到请求:{}", requestURI);
        if (checkWhiteList(requestURI)) {
            filterChain.doFilter(request, response);
            log.info("白名单,请求{}已放行", requestURI);
            return;
        }
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已登陆,session ID:{}", request.getSession().getAttribute("employee"));
            Long empID = (Long) request.getSession().getAttribute("employee");
            BaseContext.setId(empID);
            filterChain.doFilter(request, response);
            return;
        }
        //交给前端响应拦截器处理
        /*应该放在后端处理
         * */
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
        //        if (requestURI.contains("/employee/login") || requestURI.contains("/employee/logout")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        Object employee = request.getSession().getAttribute("employee");
//        if (employee == null) {
//            response.sendRedirect("/employee/login");
//            return;
//        }

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

