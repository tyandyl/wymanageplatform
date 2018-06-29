package com.wy.manage.platform.core;

/**
 * Created by tianye on 2018/6/29.
 */
public enum TagType {

    DIV(1,"div");
    private int code;
    private String name;

    TagType(int code,String name){
        this.code=code;
        this.name=name;
    }

}
