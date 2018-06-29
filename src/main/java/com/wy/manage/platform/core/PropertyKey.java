package com.wy.manage.platform.core;

import java.util.List;

/**
 * Created by tianye on 2018/6/29.
 */
public class PropertyKey implements IPropertyKey{

    private PropertyType propertyKey;

    private List<String> compatibility;

    public PropertyType getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(PropertyType propertyKey) {
        this.propertyKey=propertyKey;
    }

    public List<String> getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(List<String> compatibility) {
        this.compatibility=compatibility;
    }
}
