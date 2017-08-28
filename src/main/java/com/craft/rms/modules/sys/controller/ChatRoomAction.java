package com.craft.rms.modules.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pengpei on 2017/8/25.
 */
@Controller
@RequestMapping("sys/chat/")
public class ChatRoomAction {
    @RequestMapping("chatRoom")
    public String chatRoom(){
        return "sys/chat";
    }
}
