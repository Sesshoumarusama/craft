package com.craft.rms.modules.sys.controller;

import com.craft.rms.Base.AjaxReturnInfo;
import com.craft.rms.Base.BaseAction;
import com.craft.rms.modules.sys.model.SysUsers;
import com.craft.rms.modules.sys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Created by pengpei on 2017/8/17.
 */
@Controller
@RequestMapping("/sys/")
public class LoginAction extends BaseAction{

    @Resource
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public AjaxReturnInfo login(@RequestParam("userName") String userName,
                                @RequestParam("password") String password,
                                HttpServletRequest request){
        SysUsers user = userService.get(userName, password);
        if(user == null){
            return AjaxReturnInfo.opratedResult(FAIL, "登录", "用户名或者密码错误");
        }
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_USER, user);
        session.setAttribute(USERNAME, user.getUserName());
        return AjaxReturnInfo.opratedResult(SUCCESS, "登录");
    }

    @RequestMapping("index")
    public String index(){
        System.out.println("进来了");
        return "login";
    }



    @RequestMapping("hello")
    public String hello(String message) {
        System.out.println("message=" + message);
        return "login";
    }

}
