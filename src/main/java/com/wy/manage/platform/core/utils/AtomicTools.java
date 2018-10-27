package com.wy.manage.platform.core.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tianye
 */
public class AtomicTools {
    private static AtomicInteger ai=new AtomicInteger(0);
    public static String getUniqueInteger(){
        ai.getAndIncrement();
        return String.valueOf(ai.get());
    }
}
