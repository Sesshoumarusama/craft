package com.craft.rms.modules.sys.service;

import com.craft.rms.modules.sys.model.SysUsers;

import java.util.List;

/**
 * Created by pengpei on 2017/8/21.
 */
public interface UserService {

    int addUser(SysUsers sysUsers);

    List<SysUsers> listUser(SysUsers sysUsers);

    int editUser(SysUsers sysUsers);

    int deleteUser(Integer userId);

    SysUsers get(String userName, String password);
}
