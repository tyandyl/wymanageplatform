package com.wy.manage.platform.core.parser;

/**
 * Created by tianye
 */
public enum MeanType {
    CHANGE_MEANING(1,"转义"),
    NO_CHANGE_MEANING(2,"非转义");
    public int state;
    public String name;

    private MeanType(int state, String name){
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
