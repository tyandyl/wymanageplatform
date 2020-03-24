package com.wy.manage.platform.core.widget;

import java.util.Map;

/**
 * Created by tianye13 on 2019/3/13.
 */
public class RegisterEventManage {

    private String selectorValue;
    //这里我们设定辅助参数不能超过3个，第一个是事件
    private String[] arr=new String[4];
    //辅助参数个数，默认为0,不包括事件
    private int paramNum=0;

    private Widget widget;

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    public String getSelectorValue() {
        return selectorValue;
    }

    public void setSelectorValue(String selectorValue) {
        this.selectorValue = selectorValue;
    }

    public int getParamNum() {
        return paramNum;
    }

    public void setParamNum(int paramNum) {
        this.paramNum = paramNum;
    }

    public String[] getArr() {
        return arr;
    }

    public void setArr(String[] arr) {
        this.arr = arr;
    }
}
