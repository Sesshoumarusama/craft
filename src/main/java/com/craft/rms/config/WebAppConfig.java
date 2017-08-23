package com.craft.rms.config;

import com.craft.rms.config.filter.ExceptionFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * 给web容器配置,可以向web容器添加Filter, Servlet， Listener
 * 这里的配置类似于web.xml
 * 启动原理：
 *
 * Created by pengpei on 2017/8/18.
 */
public class WebAppConfig implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();     //配置字符过滤器，避免post提交的中文出现乱码，get请求的编码格式需要在Tomcat中的server.xml中设置
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", encodingFilter);
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");  // 配置过滤器的类型和拦截的路径


        ExceptionFilter exceptionFilter = new ExceptionFilter();
        FilterRegistration.Dynamic filter1 = servletContext.addFilter("exceptionFilter", exceptionFilter);
        filter1.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");  // 配置过滤器的类型和拦截的路径


    }
}
