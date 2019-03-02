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

    private boolean isClicked=false;

    private boolean isRecorded=false;

    private boolean isRecorded2=false;

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

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isRecorded() {
        return isRecorded;
    }

    public void setRecorded(boolean recorded) {
        isRecorded = recorded;
    }

    public boolean isRecorded2() {
        return isRecorded2;
    }

    public void setRecorded2(boolean recorded2) {
        isRecorded2 = recorded2;
    }
}
