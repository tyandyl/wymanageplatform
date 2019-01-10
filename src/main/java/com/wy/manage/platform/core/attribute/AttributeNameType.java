package com.wy.manage.platform.core.attribute;

/**
 * Created by tianye on 2018/6/29.
 */
public enum AttributeNameType {

    WIDTH(1,"width"),
    HEIGHT(2,"height"),
    POSITION(3,"position"),
    DISPLAY(4,"display"),
    TOP(5,"top"),
    LEFT(6,"left"),
    RIGHT(7,"right"),
    BOTTOM(8,"bottom");
    private int code;
    private String name;

    AttributeNameType(int code, String name){
        this.code=code;
        this.name=name;
    }

    public static AttributeNameType getAttributeNameType(String str){
        for (AttributeNameType c : AttributeNameType.values()) {
            if (str.equalsIgnoreCase(c.getName())) {
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
