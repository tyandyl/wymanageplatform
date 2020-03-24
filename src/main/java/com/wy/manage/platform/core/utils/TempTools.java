package com.wy.manage.platform.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye
 */
public class TempTools {

    public static List<String> createList(String... str){
        List<String> list=new ArrayList<String>();
        for(String st:str){
            list.add(st);
        }
        return list;
    }
}
