package com.wy.manage.platform.core.widget;

import java.io.Serializable;

/**
 * Created by tianye
 */
public class Result implements Serializable{
    private static final long serialVersionUID = 4553559610548832009L;

    private int state;
    private String message;
    private Object result;//返回对象

    public Result(){
        this.state=1;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
