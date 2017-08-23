package com.craft.rms.modules.sys.controller;

import com.craft.rms.Base.AjaxReturnInfo;
import com.craft.rms.Base.ResultStatus;
import com.craft.rms.common.D;
import com.craft.rms.modules.sys.model.SysUsers;
import com.craft.rms.modules.sys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by pengpei on 2017/8/18.
 */
@Controller
@RequestMapping("/sys/user/")
public class UserAction implements ResultStatus{
    @Resource
    private UserService userService;

    @RequestMapping("index")
    public String user(HttpServletRequest request){
        return "sys/user";
    }

    @RequestMapping("addUser")
    @ResponseBody
    public AjaxReturnInfo addUser(@ModelAttribute SysUsers sysUsers){
        sysUsers.setCreatId(1);
        sysUsers.setCreatDt(new Date());
        sysUsers.setUserStatusCd(D.UserStatus.VALID);
        sysUsers.setValidDt(new Date());
        userService.addUser(sysUsers);
        return AjaxReturnInfo.saveResult(ResultStatus.SUCCESS);
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public AjaxReturnInfo deleteUser(@RequestParam("userId") Integer userId){
        userService.deleteUser(userId);
        return AjaxReturnInfo.deleteResult(SUCCESS);
    }

    @RequestMapping("listUserInfos")
    @ResponseBody
    public AjaxReturnInfo listUserInfos(){
        return AjaxReturnInfo.listResult(userService.listUser(new SysUsers()));
    }
}
