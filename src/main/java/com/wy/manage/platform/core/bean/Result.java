package com.wy.manage.platform.core.bean;

import java.io.Serializable;

/**
 * Created by tianye
 */
public class Result implements Serializable{
    private static final long serialVersionUID = 3099744739480409557L;
    private int code;
    private String message;

    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
