package com.wy.manage.platform.core;

import java.util.List;

/**
 * Created by tianye on 2018/6/29.
 */
public class PropertyValue implements IPropertyValue{

    private String propertyValue;

    private List<String> compatibility;


    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue=propertyValue;
    }

    public List<String> getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(List<String> compatibility) {
        this.compatibility=compatibility;
    }
}
