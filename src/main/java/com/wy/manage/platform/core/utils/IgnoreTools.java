package com.wy.manage.platform.core.utils;

/**
 * Created by tianye
 */
public class IgnoreTools {

    /**
     * 不能增加去除空格，因为css value空格有可能存在
     * @param str
     * @return
     */
    public static String ignore(String str){
        String replace = str.trim().replaceAll("\\n", "").replaceAll("\\r", "").replace("\t", "");
        return replace;
    }
}
