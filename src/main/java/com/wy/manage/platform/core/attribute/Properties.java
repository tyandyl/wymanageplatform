package com.wy.manage.platform.core.attribute;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianye on 2018/8/8.
 */
public class Properties implements Serializable {

    private static final long serialVersionUID = -4141998754885245459L;

    private Map<AttributeNameType,IAttributeValue> properties;

    public Map<AttributeNameType, IAttributeValue> getProperties() {
        return properties;
    }

    public void setProperties(Map<AttributeNameType, IAttributeValue> properties) {
        this.properties = properties;
    }

    public IAttributeValue getAttributeValue(AttributeNameType attributeNameType){
        return properties.get(attributeNameType);
    }

    public Properties(){
        properties=new HashMap<AttributeNameType, IAttributeValue>();
    }

    public Properties(Map<AttributeNameType,IAttributeValue> var){
        this.properties =var;
    }
}
