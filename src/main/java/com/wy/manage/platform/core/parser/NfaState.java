package com.wy.manage.platform.core.parser;

public enum NfaState{
    START(1,"开始"),
    PROCEED(2,"进行"),
    END(3,"结束");
    public int state;
    public String name;

    private NfaState(int state,String name){
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
