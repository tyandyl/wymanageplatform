package com.wy.manage.platform.core;

import com.wy.manage.platform.core.utils.PropertyTools;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/28.
 */
public class WidgetNode implements IWidget {

    private Widget data;

    private boolean isRoot;

    //使用空间换取时间法
    private String code;

    private WidgetNode parentNode;



    public Widget getWidget() {
        return data;
    }

    public void setWidget(Widget widget) {
        this.data = widget;
    }

    public int getWidth() throws Exception {
        String value = PropertyTools.getPropertyValue(data.getProperties(), PropertyType.WIDTH);
        if(StringUtils.isNotBlank(value)){
            return Integer.valueOf(value);
        }
        return 0;
    }

    public void setWidth(int width) throws Exception {
        Property property = PropertyTools.createProperty(PropertyType.WIDTH, width);
        List<Property> ListP=new ArrayList<Property>();
        ListP.add(property);
        data.getProperties().put(PropertyType.WIDTH,ListP);
    }

    public int getHeight() throws Exception {
        String value = PropertyTools.getPropertyValue(data.getProperties(), PropertyType.HEIGHT);
        if(StringUtils.isNotBlank(value)){
            return Integer.valueOf(value);
        }
        return 0;
    }

    public void setHeight(int height) throws Exception {
        Property property = PropertyTools.createProperty(PropertyType.HEIGHT, height);
        List<Property> ListP=new ArrayList<Property>();
        ListP.add(property);
        data.getProperties().put(PropertyType.WIDTH,ListP);
    }

    public IFlow getFlow() {
        return data.getFlow();
    }

    public void setFlow(IFlow flow) {
        data.setFlow(flow);
    }

    public List<Property> getIdentifying() {
        return data.getIdentifying();
    }

    public void addIdentifying(Property iProperty) {
        data.getIdentifying().add(iProperty);
    }

    public List<Property> getClassList() {
        return  data.getClassList();
    }

    public void addClass(Property iProperty) {
        data.getClassList().add(iProperty);
    }

    public String getDescription() {
        return data.getDescription() ;
    }

    public void setDescription(String description) {
        data.setDescription(description);
    }

    public BlockType getBlockType() {
        return data.getBlockType();
    }

    public void setBlockType(BlockType blockType) {
        data.setBlockType(blockType);
    }

    public String getTitle() {
        return data.getTitle();
    }

    public void setTitle(String name) {
        data.setTitle(name);
    }


    public Map<PropertyType, List<Property>> getProperties() {
        return data.getProperties();
    }

    public void setProperties(Map<PropertyType, List<Property>> properties) {
        data.setProperties(properties);
    }

    public TagType getTagType() {
        return data.getTagType();
    }

    public void setTagType(TagType tagType) {
        data.setTagType(tagType);
    }

    public List<IBlock> getChildren() {
        return data.getChildren();
    }

    public void setChildren(List<IBlock> list) {
        data.setChildren(list);
    }

    public Widget getData() {
        return data;
    }

    public void setData(Widget data) {
        this.data = data;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public WidgetNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(WidgetNode parentNode) {
        this.parentNode = parentNode;
    }
}
