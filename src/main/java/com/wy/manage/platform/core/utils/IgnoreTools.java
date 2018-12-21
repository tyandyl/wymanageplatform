package com.wy.manage.platform.core.utils;

/**
 * Created by tianye
 */
public class IgnoreTools {

    public static String ignore(String str){
        String replace = str.trim().replaceAll("\\n", "").replaceAll("\\r", "").replace("\t", "");
        return replace;
    }
}
