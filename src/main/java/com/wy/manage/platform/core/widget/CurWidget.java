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
    //是起始控件标识
    private boolean flag=false;

    private String widgetName;

    private String proDataTitle;

    private String multiple;
    //<option value="32"> 这种的属性值
    private String value;
    //button 点击跳转用
    private String url;

    private boolean urlIsDefault=false;

    private String dataFlag;

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public String getProDataTitle() {
        return proDataTitle;
    }

    public void setProDataTitle(String proDataTitle) {
        this.proDataTitle = proDataTitle;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUrlIsDefault() {
        return urlIsDefault;
    }

    public void setUrlIsDefault(boolean urlIsDefault) {
        this.urlIsDefault = urlIsDefault;
    }

    public String getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
    }
}
