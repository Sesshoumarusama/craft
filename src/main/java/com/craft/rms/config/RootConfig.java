package com.craft.rms.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

/**
 * Created by pp on 2017/8/17.
 * spring 上下文配置类
 *
 */
@Configuration
@Import(value = {MyBtaisConfig.class})
@ComponentScan(basePackages = "com.craft.rms",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class RootConfig {


}
