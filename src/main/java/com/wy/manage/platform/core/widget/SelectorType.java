package com.wy.manage.platform.core.widget;

/**
 * Created by tianye on 2018/7/5.
 */
public enum SelectorType {

    ID_SELECTOR(1,"id"),
    CLASS_SELECTOR(2,"class"),
    GROUP_SELECTOR(3,"group"),
    ATTRIBUTE_SELECTOR(4,"attribute")
    ;
    private int code;
    private String name;

    SelectorType(int code,String name){
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

    public static SelectorType getSelectorType(String str){
        for (SelectorType c : SelectorType.values()) {
            if (c.getName().equalsIgnoreCase(str)) {
                return c;
            }
        }
        return null;
    }



}
