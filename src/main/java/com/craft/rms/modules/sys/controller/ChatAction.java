package com.craft.rms.modules.sys.controller;

import com.alibaba.fastjson.JSON;
import com.craft.rms.modules.sys.model.message.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengpei on 2017/8/25.
 */
@RestController
public class ChatAction {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ChatMessage broadcast(ChatMessage chatMessage){
        System.out.println("收到的信息：" + JSON.toJSONString(chatMessage));
        return chatMessage;
    }
}
