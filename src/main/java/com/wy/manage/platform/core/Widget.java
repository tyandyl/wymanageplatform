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

    private IFlow flow;

    private List<Property> identifyingList;

    private List<Property> classList;

    private String description;

    private BlockType blockType;

    private String title;

    private TagType tagType;

    private List<IBlock> children;


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
        return flow;
    }

    public void setFlow(IFlow flow) {
        this.flow=flow;
    }

    public List<Property> getIdentifying() {
        return identifyingList;
    }

    public void addIdentifying(Property iProperty) {
        identifyingList.add(iProperty);
    }

    public List<Property> getClassList() {
        return classList;
    }

    public void addClass(Property iProperty) {
        classList.add(iProperty);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType=blockType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title=name;
    }

    public Map<PropertyType, List<Property>> getProperties() {
        return properties;
    }

    public void setProperties(Map<PropertyType, List<Property>> properties) {
        this.properties=properties;
    }

    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType=tagType;
    }

    public List<IBlock> getChildren() {
        return children;
    }

    public void setChildren(List<IBlock> list) {
        this.children=list;
    }


}
