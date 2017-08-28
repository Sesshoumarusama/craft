package com.craft.rms.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by pp on 2017/8/17.
 * spring 上下文配置类
 *
 */
@Configuration
@Import(value = {MyBtaisConfig.class})
@ComponentScan(basePackages = "com.craft.rms",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
                          @ComponentScan.Filter(type =  FilterType.ANNOTATION, value = Configuration.class)})
public class RootConfig {

    /**
     * 配置mybatis的事务管理类
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    /**
     * 配置事务拦截器
     * @param transactionManager
     * @return
     */
    @Bean
    public TransactionInterceptor transactionInterceptor(DataSourceTransactionManager transactionManager){
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(transactionManager);
        Properties p = new Properties();
        p.setProperty("*", "PROPAGATION_REQUIRED");
        transactionInterceptor.setTransactionAttributes(p);
        return transactionInterceptor;
    }



}
