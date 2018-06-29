package com.wy.manage.platform.core;

import com.wy.manage.platform.core.utils.PropertyTools;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/29.
 */
public class Widget implements IWidget{

    private Map<PropertyType,List<Property>> properties;


    public int getWidth() throws Exception {
        String value = PropertyTools.getPropertyValue(properties, PropertyType.WIDTH);
        if(StringUtils.isNotBlank(value)){
            return Integer.valueOf(value);
        }
        return 0;
    }

    public void setWidth(int width) throws Exception {
        Property property = PropertyTools.createProperty(PropertyType.WIDTH, width);
        List<Property> ListP=new ArrayList<Property>();
        ListP.add(property);
        properties.put(PropertyType.WIDTH,ListP);
    }

    public int getHeight() throws Exception {
        String value = PropertyTools.getPropertyValue(properties, PropertyType.HEIGHT);
        if(StringUtils.isNotBlank(value)){
            return Integer.valueOf(value);
        }
        return 0;
    }

    public void setHeight(int height) throws Exception {
        Property property = PropertyTools.createProperty(PropertyType.HEIGHT, height);
        List<Property> ListP=new ArrayList<Property>();
        ListP.add(property);
        properties.put(PropertyType.WIDTH,ListP);
    }

    public IFlow getFlow() {
        return null;
    }

    public void setFlow(IFlow flow) {

    }

    public List<Property> getIdentifying() {
        return null;
    }

    public void addIdentifying(Property iProperty) {

    }

    public List<Property> getClassList() {
        return null;
    }

    public void addClass(Property iProperty) {

    }

    public String getDescription() {
        return null;
    }

    public void setDescription(String description) {

    }

    public BlockType getBlockType() {
        return null;
    }

    public void setBlockType(BlockType blockType) {

    }

    public String getTitle() {
        return null;
    }

    public void setTitle(String name) {

    }

    public Map<PropertyType, List<Property>> getProperties() {
        return null;
    }

    public void setProperties(Map<PropertyType, List<Property>> properties) {

    }

    public TagType getTagType() {
        return null;
    }

    public void setTagType(TagType tagType) {

    }

    public List<IBlock> getChildren() {
        return null;
    }

    public void setChildren(List<IBlock> list) {

    }
}
