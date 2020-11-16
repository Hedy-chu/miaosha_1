package com.miaosha_1.redis;

/**
 * @author qqtang
 * @Date 2020/11/16 17:24
 * @Desc
 */
public interface KeyPrefix {
    public int expireSecond();
    public String getPrefix();
}
