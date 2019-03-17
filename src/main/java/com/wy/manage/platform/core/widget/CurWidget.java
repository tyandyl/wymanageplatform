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
    private String outContentValue;
    private RegisterParam registerParam=new RegisterParam();


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



    public String getOutContentValue() {
        return outContentValue;
    }

    public void setOutContentValue(String outContentValue) {
        this.outContentValue = outContentValue;
    }

    public RegisterParam getRegisterParam() {
        return registerParam;
    }

    public void setRegisterParam(RegisterParam registerParam) {
        this.registerParam = registerParam;
    }
}
