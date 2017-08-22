package com.craft.rms.config.interceptor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by pengpei on 2017/8/22.
 */
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger;

    public LoginInterceptor(){
        logger = LoggerFactory.getLogger(this.getClass());
    }

    private String[] excludedUrls = {
            "/sys/login"
    };

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("拦截请求<{}>,进行权限验证的处理", request.getRequestURI());
        // excluded URLs:
        /*String requestUri = request.getRequestURI();
        for (String url : excludedUrls) {
            if (requestUri.contains(url)) {
                return true;
            }
        }*/

        // check login
        HttpSession session = request.getSession();
        if (session.getAttribute("userName") == null) {
            if (request.getHeader("x-requested-with") != null &&
                    request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                System.out.println(request.getHeader("x-requested-with"));
                //重定向到登录页面
                //异步请求session超时该怎么处理

            } else if(request.getHeader("x-requested-with") == null){
                System.out.println("请求类型" + JSON.toJSONString(request.getHeaderNames()));
                response.sendRedirect(request.getContextPath() + "/sys/index");
            } else {
                // 普通http请求session超时的处理
                throw new AuthenticationException();
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
