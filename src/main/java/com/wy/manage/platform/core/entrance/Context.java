package com.wy.manage.platform.core.entrance;

import com.wy.manage.platform.core.widget.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianye13 on 2019/1/29.
 */
public class Context {
    private static Map<String,Page> map=new HashMap<String, Page>();

    public static Page get(String str){
        return map.get(str);
    }

    public static void put(String str,Page page){
        map.put(str,page);
    }

    public static void clear(){
        map.clear();
    }
}
