package com.craft.rms.modules.sys.model.message;

import lombok.Data;

/**
 * Created by pengpei on 2017/8/25.
 */
@Data
public class ChatMessage {
    public Integer userId;

    public String userName;

    public String content;
}
