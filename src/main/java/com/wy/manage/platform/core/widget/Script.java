package com.wy.manage.platform.core.widget;

import java.io.Serializable;

/**
 * Created by tianye on 2018/8/13.
 */
public class Script implements Serializable {
    private static final long serialVersionUID = 4980358253536850049L;
    private String type;
    private String src;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
