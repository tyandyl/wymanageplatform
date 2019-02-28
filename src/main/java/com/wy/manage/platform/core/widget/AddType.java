package com.wy.manage.platform.core.widget;

/**
 * Created by tianye
 */
public enum AddType {
    PAGE(1,"page"),
    WIDGET(2,"widget")
    ;
    private int code;
    private String name;

    AddType(int code,String name){
        this.code=code;
        this.name=name;
    }
    public static AddType getAddType(int code){
        for (AddType c : AddType.values()) {
            if (c.getCode()==code) {
                return c;
            }
        }
        return null;
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
