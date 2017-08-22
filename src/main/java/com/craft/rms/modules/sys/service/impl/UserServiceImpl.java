package com.craft.rms.modules.sys.service.impl;

import com.craft.rms.Base.LogProvider;
import com.craft.rms.modules.sys.dao.UserDao;
import com.craft.rms.modules.sys.model.SysUsers;
import com.craft.rms.modules.sys.service.UserService;
import com.craft.rms.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pengpei on 2017/8/21.
 */
@Service
public class UserServiceImpl extends LogProvider implements UserService {

    @Resource
    private UserDao userDao;

    public int addUser(SysUsers sysUsers) {
        return userDao.insert(sysUsers);
    }

    public List<SysUsers> listUser(SysUsers sysUsers) {
        return userDao.selectByCondition(sysUsers);
    }

    public int editUser(SysUsers sysUsers) {
        if(sysUsers.getUserId() == null){
            throw new IllegalArgumentException("user id 不能为空");
        }
        return userDao.updateByPrimaryKeySelective(sysUsers);
    }

    public int deleteUser(Integer userId) {
        return userDao.deleteByPrimaryKey(userId);
    }

    public SysUsers get(String userName, String password) {
        if(!StringUtils.hasText(userName) || !StringUtils.hasText(password)){
            throw new RuntimeException("账户密码不能为空");
        }
        return userDao.getByUserNameAndPassword(userName, password);
    }
}
