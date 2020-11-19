package com.miaosha_1.util;

import java.util.UUID;

/**
 * @author qqtang
 * @Date 2020/11/18 14:51
 * @Desc
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
