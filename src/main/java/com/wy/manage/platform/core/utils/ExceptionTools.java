package com.wy.manage.platform.core.utils;


import org.apache.commons.lang.StringUtils;

/**
 * Created by tianye on 2018/7/5.
 */
public class ExceptionTools {

    public static void argumentCanNotBeNull(String argument, String var) throws Exception{
        if(StringUtils.isBlank(argument)) {
            throw new Exception(var + " can not be null");
        }
    }
}
