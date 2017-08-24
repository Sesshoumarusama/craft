package com.craft.rms.config.interceptor;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 登录验证的拦截器
 * Created by pengpei on 2017/8/22.
 */
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger;

    public LoginInterceptor(){
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(logger.isDebugEnabled()){
            logger.debug("拦截请求<{}>,进行权限验证的处理", request.getRequestURI());
        }
        // check login
        HttpSession session = request.getSession();
        if (session.getAttribute("userName") == null) {
            if (request.getHeader("x-requested-with") != null &&
                    request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                System.out.println(request.getHeader("x-requested-with"));
                //重定向到登录页面
                //异步请求session超时该怎么处理,需从浏览器端发送一个到登录页面的请求
                response.reset();
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write("timeout");
                writer.flush();
                return false;//不再执行后续的拦截
            } else if(request.getHeader("x-requested-with") == null){
                System.out.println("请求类型" + JSON.toJSONString(request.getHeader("upgrade-insecure-requests")));
                response.sendRedirect(request.getContextPath() + "/sys/login/index");
                return false;
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
