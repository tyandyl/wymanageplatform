package com.wy.manage.platform.core.attribute;

import com.wy.manage.platform.core.widget.SelectorType;
import com.wy.manage.platform.core.widget.StyleSheetType;

/**
 * Created by tianye on 2018/8/8.
 */
public interface IAttributeValue {

    public StyleSheetType getStyleSheetType();

    public void setStyleSheetType(StyleSheetType styleSheetType);

    public SelectorType getSelectorType();

    public void setSelectorType(SelectorType selectorType);

    public String getSelectorName() ;

    public void setSelectorName(String selectorName);

    public String getAttributeValue();

    public void setAttributeValue(String attributeValue);

    public boolean isValueIsKeyWord() ;

    public void setValueIsKeyWord(boolean valueIsKeyWord);
}
