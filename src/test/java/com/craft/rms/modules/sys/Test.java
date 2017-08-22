package com.craft.rms.modules.sys;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by pengpei on 2017/8/21.
 */
public class Test {

    @org.junit.Test
    public void test1(){
        Resource resource = new ClassPathResource("classpath:mappings/**/*Dao.xml");
    }
}
