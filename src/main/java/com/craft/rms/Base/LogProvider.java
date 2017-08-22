package com.craft.rms.Base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提供日志监控
 * Created by pengpei on 2017/8/22.
 */
public abstract class LogProvider {
    public Logger logger;

    public LogProvider(){
        logger = LoggerFactory.getLogger(this.getClass());
    }
}
