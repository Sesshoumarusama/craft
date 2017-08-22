package com.craft.rms.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 */
@Order(1)    //定义前端控制器的随Servlet容器启动的顺序，为立即启动
public class WebAppInitialer extends AbstractAnnotationConfigDispatcherServletInitializer{

    /**
     * 定义spring上下文配置类
     * @return
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 加载SpringMVC的配置类
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    /**
     * 配置前端控制器的映射路径
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 不必
     * @return
     */

    /*@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }*/
}
