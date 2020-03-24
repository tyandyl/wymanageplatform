package com.wy.manage.platform.core.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tianye
 */
public class AtomicTools {
    private static AtomicInteger ai=new AtomicInteger(0);
    //因为在给节点赋值状态码时,ai的有混淆，所以使用bi
    private static AtomicInteger bi=new AtomicInteger(0);
    public static String getUniqueInteger(){
        ai.getAndIncrement();
        return String.valueOf(ai.get());
    }

    public static Integer getBiUniqueInteger(){
        bi.getAndIncrement();
        return bi.get();
    }

}
