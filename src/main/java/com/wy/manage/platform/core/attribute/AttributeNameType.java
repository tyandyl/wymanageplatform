package com.wy.manage.platform.core.attribute;

/**
 * Created by tianye on 2018/6/29.
 */
public enum AttributeNameType {

    WIDTH(1,"width"),
    HEIGHT(2,"height");
    private int code;
    private String name;

    AttributeNameType(int code, String name){
        this.code=code;
        this.name=name;
    }


}
