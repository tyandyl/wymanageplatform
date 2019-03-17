package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.AttributeNameType;
import com.wy.manage.platform.core.attribute.AttributeValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/28.
 */
public class WidgetNode implements Serializable{

    private static final long serialVersionUID = -4783670709072665624L;
    private Widget data;

    //使用空间换取时间法
    private String fullCode;

    private String code;

    private WidgetNode parentNode;

    private boolean isFirstClosed=false;

    private List<WidgetNode> childNodes=new ArrayList<WidgetNode>();


    public Widget getData() {
        return data;
    }

    public void setData(Widget data) {
        this.data = data;
    }

    public WidgetNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(WidgetNode parentNode) {
        this.parentNode = parentNode;
    }


    public String getFullCode() {
        return fullCode;
    }

    public void setFullCode(String fullCode) {
        this.fullCode = fullCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<WidgetNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<WidgetNode> childNodes) {
        this.childNodes = childNodes;
    }

    public boolean isFirstClosed() {
        return isFirstClosed;
    }

    public void setFirstClosed(boolean firstClosed) {
        isFirstClosed = firstClosed;
    }
}
