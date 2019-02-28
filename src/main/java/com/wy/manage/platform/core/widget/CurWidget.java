package com.wy.manage.platform.core.widget;

/**
 * Created by tianye
 */
public class CurWidget {
    private String parentWd;
    private String parentTagName;
    private String curWd;
    private String curTagName;
    private String curPros;

    private boolean isMoved=false;

    private boolean isClick=false;

    private boolean isRecord=false;

    public String getCurWd() {
        return curWd;
    }

    public void setCurWd(String curWd) {
        this.curWd = curWd;
    }

    public String getCurTagName() {
        return curTagName;
    }

    public void setCurTagName(String curTagName) {
        this.curTagName = curTagName;
    }

    public String getParentWd() {
        return parentWd;
    }

    public void setParentWd(String parentWd) {
        this.parentWd = parentWd;
    }

    public String getCurPros() {
        return curPros;
    }

    public void setCurPros(String curPros) {
        this.curPros = curPros;
    }

    public String getParentTagName() {
        return parentTagName;
    }

    public void setParentTagName(String parentTagName) {
        this.parentTagName = parentTagName;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public boolean isRecord() {
        return isRecord;
    }

    public void setRecord(boolean record) {
        isRecord = record;
    }
}
