package com.wy.manage.platform.core.parser;

/**
 * Created by tianye13 on 2018/9/7.
 */
public enum EdgeInputType {
    NULL_GATHER(1,"空集输入"),
    //CHARACTER_ALONE(2,"单个字符输入"),
    CHARACTER_REPERTOIRE(3,"有限字符集输入"),
    ANY(4,"任何字符输入");
    public int state;
    public String name;

    private EdgeInputType(int state, String name){
        this.state=state;
        this.name=name;
    }
    public int getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
