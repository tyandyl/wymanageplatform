package com.wy.manage.platform.core.widget;

/**
 * Created by tianye on 2018/7/5.
 */
public enum StyleSheetType {
    EXTERNAL(1,"External"),
    INTERNAL(2,"Internal"),
    INLINE(3,"Inline");
    private int code;
    private String name;

    StyleSheetType(int code,String name){
        this.code=code;
        this.name=name;
    }

}
