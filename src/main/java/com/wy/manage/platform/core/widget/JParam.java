package com.wy.manage.platform.core.widget;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianye13 on 2019/2/22.
 */
public class JParam implements Serializable {
    private static final long serialVersionUID = -3670349063954746718L;

    private String parentWd;



    public String getParentWd() {
        return parentWd;
    }

    public void setParentWd(String parentWd) {
        this.parentWd = parentWd;
    }

}
