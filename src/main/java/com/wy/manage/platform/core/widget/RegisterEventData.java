package com.wy.manage.platform.core.widget;

/**
 * Created by tianye13 on 2019/3/13.
 */
public class RegisterEventData {
    private String selectorValue;
    private int i;

    public RegisterEventData(String selectorValue, int i){
        this.selectorValue=selectorValue;
        this.i=i;
    }

    public String getSelectorValue() {
        return selectorValue;
    }

    public void setSelectorValue(String selectorValue) {
        this.selectorValue = selectorValue;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
