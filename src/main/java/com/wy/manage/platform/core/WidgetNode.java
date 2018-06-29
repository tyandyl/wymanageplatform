package com.wy.manage.platform.core;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/28.
 */
public class WidgetNode implements IWidget {

    public int getWidth() {
        return 0;
    }

    public void setWidth(int width) {

    }

    public int getHeight() {
        return 0;
    }

    public void setHeight(int height) {

    }

    public IFlow getFlow() {
        return null;
    }

    public void setFlow(IFlow flow) {

    }

    public List<IProperty> getIdentifying() {
        return null;
    }

    public void addIdentifying(IProperty iProperty) {

    }

    public List<IProperty> getClassList() {
        return null;
    }

    public void addClass(IProperty iProperty) {

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

    public Map<PropertyType, List<IProperty>> getProperties() {
        return null;
    }

    public void setProperties(Map<PropertyType, List<IProperty>> properties) {

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
