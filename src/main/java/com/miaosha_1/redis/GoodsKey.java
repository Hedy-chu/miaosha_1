package com.miaosha_1.redis;

/**
 * @author qqtang
 * @Date 2020/11/16 17:30
 * @Desc
 */
public class GoodsKey extends BasePrefix {
    private GoodsKey(int expireSecond,String prefix) {
        super(expireSecond,prefix);
    }
    public static GoodsKey getGoodsList = new GoodsKey(60,"gl");
    public static GoodsKey getGoodsDeDetail = new GoodsKey(60,"gd");

}
