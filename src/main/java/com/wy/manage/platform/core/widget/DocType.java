package com.wy.manage.platform.core.widget;

/**
 * Created by tianye
 */
public enum DocType {
    HTML5(1,"html5"),
    TRANSITIONAL(2,"transitional"),
    STRICT(3,"strict"),
    FRAMESET(4,"frameSet"),
    MOBILE(5,"mobile")
    ;

    private int code;
    private String name;

    DocType(int code,String name){
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
