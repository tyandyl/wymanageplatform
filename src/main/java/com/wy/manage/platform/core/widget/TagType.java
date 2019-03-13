package com.wy.manage.platform.core.widget;

/**
 * Created by tianye on 2018/6/29.
 */
public enum TagType {

    DIV(1,"div"),
    BUTTON(2,"button"),
    TABLE(3,"table"),
    TR(4,"tr"),
    TD(5,"td"),
    INPUT(6,"input"),
    LEGEND(7,"legend"),
    FORM(8,"form"),
    FIELD_SET(9,"fieldset")
    ;
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
