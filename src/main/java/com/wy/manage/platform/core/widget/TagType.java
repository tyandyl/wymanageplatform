package com.wy.manage.platform.core.widget;

/**
 * Created by tianye on 2018/6/29.
 */
public enum TagType {

    DIV(1,"div"),
    BUTTON(2,"button");
    private int code;
    private String name;

    TagType(int code,String name){
        this.code=code;
        this.name=name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
