package com.wy.manage.platform.core.widget;

import java.io.Serializable;

/**
 * Created by tianye13 on 2018/8/13.
 */
public class Link implements Serializable {

    private static final long serialVersionUID = 6074987388704658808L;

    private String rel;
    private String style;
    private String href;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
