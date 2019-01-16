package com.wy.manage.platform.core.attribute;

import java.util.ArrayList;
import java.util.List;

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
    BOTTOM(8,"bottom"),
    BACKGROUND(9,"background"),
    BORDERWIDTH(10,"border-width"),
    BORDERSTYLE(11,"border-style"),
    BORDERCOLOR(12,"border-color"),
    BACKGROUNDCOLOR(13,"background-color");
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

    public static List<String> getNameList(){
        List<String> list=new ArrayList<String>();
        list.add(DISPLAY.getName());
        list.add(POSITION.getName());
        list.add(WIDTH.getName());
        list.add(HEIGHT.getName());
        list.add(TOP.getName());
        list.add(LEFT.getName());
        list.add(RIGHT.getName());
        list.add(BOTTOM.getName());
        list.add(BACKGROUND.getName());
        list.add(BORDERWIDTH.getName());
        list.add(BORDERSTYLE.getName());
        list.add(BORDERCOLOR.getName());
        list.add(BACKGROUNDCOLOR.getName());
        return list;

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
