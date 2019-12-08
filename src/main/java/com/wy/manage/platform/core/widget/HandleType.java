package com.wy.manage.platform.core.widget;

/**
 * Created by tianye
 */
public enum HandleType {
    NEW_PAGE(1,"new_page"),
    NEW_WIDGET(2,"new_widget"),
    EDIT_WIDGET(3,"edit_widget"),
    SEND(0,"edit_widget")
    ;
    private int code;
    private String name;

    HandleType(int code, String name){
        this.code=code;
        this.name=name;
    }
    public static HandleType getHandleType(int code){
        for (HandleType c : HandleType.values()) {
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
