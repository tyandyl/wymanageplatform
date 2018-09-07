package com.wy.manage.platform.core.attribute;

import com.wy.manage.platform.core.widget.SelectorType;
import com.wy.manage.platform.core.widget.StyleSheetType;

import java.io.Serializable;

/**
 * Created by tianye on 2018/6/29.
 */
public class AttributeValue implements IAttributeValue, Serializable {

    private static final long serialVersionUID = 9041992567868286746L;

    /**
     * 样式表
     */
    private StyleSheetType styleSheetType;

    /**
     *当属性值关联选择器类型
     */
    private SelectorType selectorType;

    /**
     * 关联选择器的值
     */
    private String selectorName;

    private String attributeValue;

    private boolean valueIsKeyWord;

    public StyleSheetType getStyleSheetType() {
        return styleSheetType;
    }

    public void setStyleSheetType(StyleSheetType styleSheetType) {
        this.styleSheetType = styleSheetType;
    }

    public SelectorType getSelectorType() {
        return selectorType;
    }

    public void setSelectorType(SelectorType selectorType) {
        this.selectorType = selectorType;
    }

    public String getSelectorName() {
        return selectorName;
    }

    public void setSelectorName(String selectorName) {
        this.selectorName = selectorName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public boolean isValueIsKeyWord() {
        return valueIsKeyWord;
    }

    public void setValueIsKeyWord(boolean valueIsKeyWord) {
        this.valueIsKeyWord = valueIsKeyWord;
    }
}
