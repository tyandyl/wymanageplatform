package com.wy.manage.platform.core.bean;

/**
 * Created by tianye
 */
public class DblClickResult extends Result{
    private static final long serialVersionUID = 9103242873123519966L;
    private String tagName;
    private String wd;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }
}
