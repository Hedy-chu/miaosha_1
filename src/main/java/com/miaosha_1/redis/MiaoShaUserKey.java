package com.miaosha_1.redis;

/**
 * @author qqtang
 * @Date 2020/11/16 17:30
 * @Desc
 */
public class MiaoShaUserKey extends BasePrefix {
    public static int expireSecond = 3600*24*2;
    private MiaoShaUserKey(int expireSecond,String prefix) {
        super(expireSecond,prefix);
    }
    public static MiaoShaUserKey token = new MiaoShaUserKey(expireSecond,"tk");
    public static MiaoShaUserKey getById = new MiaoShaUserKey(0,"id");

}
