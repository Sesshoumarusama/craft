package com.craft.rms.utils;

/**
 * Created by pengpei on 2017/8/22.
 */
public final class StringUtils extends org.springframework.util.StringUtils {

    public final static String EMPTY = "";

    public static String joinStr(Object... strs) {
        if(strs == null || strs.length <= 1){
            throw new IllegalArgumentException("要拼接的字符串的个数至少为2个。。。");
        }

        StringBuilder sb = new StringBuilder();
        for (Object obj : strs){
            if(obj != null){
                sb.append(obj);
            }
        }
        return sb.toString();
    }

    public static void joinStr(StringBuilder sb, Object... strs){
        if (sb == null){
            throw new IllegalArgumentException("StringBuilder 对象没有被实例化。。。");
        }
        for (Object obj : strs){
            if(obj != null){
                sb.append(obj);
            }
        }
    }
}
