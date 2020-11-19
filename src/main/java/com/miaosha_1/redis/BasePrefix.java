package com.miaosha_1.redis;


/**
 * @author qqtang
 * @Date 2020/11/16 17:26
 * @Desc
 */
public abstract class BasePrefix implements KeyPrefix {
    private int expireSecond;
    private String prefix;

    public BasePrefix(int expireSecond, String prefix) {
        this.expireSecond = expireSecond;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        this(0,prefix);
    }

    @Override
    public int expireSecond() {//默认0代表永不过期
        return expireSecond;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+": "+prefix;
    }
}
