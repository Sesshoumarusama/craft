package com.craft.rms.Base;

import com.craft.rms.modules.sys.model.SysUsers;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pengpei on 2017/8/22.
 * 使用处理session
 */
@SessionAttributes(types = {SysUsers.class, String.class, Integer.class}, value = {"sysUsers", "userName", "userId"})
public abstract class BaseAction extends LogProvider implements ResultStatus{

    public static final String SESSION_USER = "sysUsers";
    public static final String USERNAME = "userName";
    public static final String USERID = "userId";

    public SysUsers getSysUserBySession(HttpServletRequest request){
        return (SysUsers) request.getAttribute(SESSION_USER);
    }

    public Integer getUserIdBySession(HttpServletRequest request){
        return getSysUserBySession(request).getUserId();
    }
}
