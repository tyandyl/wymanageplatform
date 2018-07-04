package com.wy.manage.platform.core.utils;

import com.wy.manage.platform.core.*;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/29.
 */
public class PropertyTools {

    /**
     * 创建属性
     * @param propertyType
     * @param value
     * @return
     * @throws Exception
     */
    public static Property createProperty(PropertyType propertyType,int value)throws Exception{
        Property property=new Property();
        PropertyKey propertyKey=new PropertyKey();
        propertyKey.setPropertyKey(propertyType);
        property.setPropertyKey(propertyKey);
        PropertyValue propertyValue=new PropertyValue();
        propertyValue.setPropertyValue(String.valueOf(value));
        property.setPropertyValue(propertyValue);
        return property;
    }

    /**
     * 获取属性值
     * @param properties
     * @param propertyType
     * @return
     * @throws Exception
     */
    public static String getPropertyValue(Map<PropertyType,List<Property>> properties,PropertyType propertyType)throws Exception{
        List<Property> WidthProperties = properties.get(propertyType);
        String value=null;
        if(WidthProperties!=null && WidthProperties.size()>0){
            Property property = WidthProperties.get(0);
            IPropertyValue propertyValue = property.getPropertyValue();
            value = propertyValue.getPropertyValue();
        }
        return value;
    }
}
