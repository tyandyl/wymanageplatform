package com.wy.manage.platform.core;

/**
 * Created by tianye on 2018/6/29.
 */
public enum PropertyType {

    WIDTH(1,"width"),
    HEIGHT(2,"height");
    private int code;
    private String name;

    PropertyType(int code,String name){
        this.code=code;
        this.name=name;
    }

}
