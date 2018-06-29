package com.wy.manage.platform.core;

import java.util.List;

/**
 * Created by tianye on 2018/6/29.
 * 属性类
 */
public class Property {

    private IPropertyKey propertyKey;

    private IPropertyValue propertyValue;

    public IPropertyKey getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(IPropertyKey propertyKey) {
        this.propertyKey = propertyKey;
    }

    public IPropertyValue getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(IPropertyValue propertyValue) {
        this.propertyValue = propertyValue;
    }
}
