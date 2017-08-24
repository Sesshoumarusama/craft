package com.craft.rms.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.craft.rms.config.interceptor.LoginInterceptor;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by pp on 2017/8/17.
 * 定义springMVC的配置
 */
@Configuration     //标注为被spring识别的配置类
@EnableWebMvc      //启用springMVC注解
@ComponentScan(basePackages = "com.craft.rms",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)},
        useDefaultFilters = false)
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    /**
     * 配置JSP的视图解析器
     * @return
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setContentType("text/html; charset=utf-8");
        internalResourceViewResolver.setOrder(0);
        return internalResourceViewResolver;
    }


    /**
     * 定义Thymeleaf视图解析器
     * @param templateEngine
     * @return
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(1);
        return viewResolver;
    }

    /**
     * 定义模板引擎
     * @param templateResolver
     * @return
     */
    @Bean
    public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver templateResolver){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    /**
     * 定义模板解析器
     * @return
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /**
     * 匹配不到的路径使用默认的servlet路径
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 配置静态资源映射路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    /*@Bean
    public RequestMappingHandlerAdapter convert(){
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        adapter.setIgnoreDefaultModelOnRedirect(true);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<HttpMessageConverter<?>> converters = Lists.newArrayList();
        converters.add(converter);
        adapter.setMessageConverters(converters);
        return adapter;
    }*/

    /**
     * 配置异步消息处理转换器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setCharset(Charset.forName("UTF-8"));
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Lists.newArrayList(
            MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN));
        converters.add(fastJsonHttpMessageConverter);
//        fastJsonHttpMessageConverter.setDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        //配置登录session验证的拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/sys/login/index", "/sys/login");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
//        registry.addViewController("/").setViewName("forward:/login");
    }
}
