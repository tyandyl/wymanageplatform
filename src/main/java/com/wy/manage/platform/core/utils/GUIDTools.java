package com.wy.manage.platform.core.utils;

import java.util.UUID;

/**
 * Created by tianye on 2018/8/9.
 */
public class GUIDTools {

    public static String  randomUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
