package com.sorm.util;

/**
 * 
 * ClassName: StringUtils
 * @author chenyiAlone  
 * Create Time: 2018/01/05 16:52:10
 * Description: 字符串工具类
 * 1. 将字符创的首字母转化为大写
 */
public class StringUtils {
    
    /**
     * 将首字母转化为大写字母
     * 
     * @param str 原字符串
     * @return 大写字母开头的字符串
     */
    public static String firstCharUpperCase(String str) {

        return str.toUpperCase().substring(0, 1) + str.substring(1);
    }

    public static void main(String[] args) {
        System.out.println(firstCharUpperCase("firstCharUpperCase"));
    }
}
