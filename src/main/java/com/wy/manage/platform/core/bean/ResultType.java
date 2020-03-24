package com.wy.manage.platform.core.bean;

/**
 * Created by tianye
 */
public enum ResultType {
    SUCCESS(1,"success"),
    FAILED(2,"failed")
    ;

    private int code;
    private String name;

    ResultType(int code,String name){
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
