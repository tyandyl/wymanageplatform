package com.wy.manage.platform.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/29.
 */
public class Test {
    public static void main(String[] agrs){
        Map<PropertyType,String> properties=new HashMap<PropertyType,String>();

        properties.put(PropertyType.WIDTH,"2");
        System.out.println("sssssssss"+properties.get(PropertyType.WIDTH));

    }
}
