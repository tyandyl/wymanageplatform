package com.wy.manage.platform.core.widget;

/**
 * Created by tianye on 2018/8/8.
 */
public enum BrowserType {
    IE(1,"ie"),
    FIREFOX(2,"Firefox")
    ;

    private int code;
    private String name;

    BrowserType(int code,String name){
        this.code=code;
        this.name=name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
