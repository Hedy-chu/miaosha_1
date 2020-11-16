package com.miaosha_1.redis;

/**
 * @author qqtang
 * @Date 2020/11/16 17:30
 * @Desc
 */
public class UserKey extends BasePrefix {
    private UserKey(String prefix) {
        super(prefix);
    }
    public static UserKey getById = new UserKey("id");
    public static UserKey grtByName = new UserKey("name");

}
