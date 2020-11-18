package com.miaosha_1.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qqtang
 * @Date 2020/11/17 16:01
 * @Desc
 */
public class ValidatorUtil {
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if (StringUtils.isEmpty(src)){
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("15712341234"));
        System.out.println(isMobile("12245674567"));
    }
}
