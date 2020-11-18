package com.miaosha_1.util;

import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author qqtang
 * @Date 2020/11/17 10:43
 * @Desc
 */
public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static final String salt = "1a2b3c4d";

    public static String inputPassToFromPass(String inputPass){
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String fromPassToDBPass(String fromPass,String salt){
        String str = "" + salt.charAt(0) + salt.charAt(2) + fromPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass,String saltDB){
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToFromPass("123456"));
        System.out.println(fromPassToDBPass("d3b1294a61a07da9b49b6e22b2cbd7f9","1a2b3c4d"));
        System.out.println(inputPassToDBPass("123456","1a2b3c4d"));
    }
}
