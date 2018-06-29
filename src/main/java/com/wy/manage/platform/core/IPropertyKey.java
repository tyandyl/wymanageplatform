package com.wy.manage.platform.core;

/**
 * Created by tianye on 2018/6/28.
 */
public interface IPropertyKey extends ICompatibility {

    PropertyType getPropertyKey();

    void setPropertyKey(PropertyType propertyKey);
}
