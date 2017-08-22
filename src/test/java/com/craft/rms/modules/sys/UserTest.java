package com.craft.rms.modules.sys;

import com.craft.rms.config.RootConfig;
import com.craft.rms.config.WebMvcConfig;
import com.craft.rms.modules.sys.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;


/**
 * Created by pengpei on 2017/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfig.class, RootConfig.class})
public class UserTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Test
    public void test(){
        System.out.println(webApplicationContext.getClass());
        UserDao userDao = webApplicationContext.getBean("userDao", UserDao.class);
        System.out.println(userDao.selectByPrimaryKey(1));
    }
}
