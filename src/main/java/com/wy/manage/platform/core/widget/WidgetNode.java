package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.AttributeNameType;
import com.wy.manage.platform.core.attribute.AttributeValue;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/28.
 */
public class WidgetNode {

    private Widget data;

    private boolean isRoot;

    //使用空间换取时间法
    private String code;

    private WidgetNode parentNode;


    public String getWidth() throws Exception {
        return data.getWidth();
    }

    public void setWidth(String var) throws Exception {
        data.setWidth(var);
    }

    public String getHeight() throws Exception {
        return data.getHeight();
    }

    public void setHeight(String var) throws Exception {
        data.setHeight(var);
    }

    public IFlow getFlow() {
        return data.getFlow();
    }

    public void setFlow(IFlow var) {
        data.setFlow(var);
    }

    public String getDescription() {
        return data.getDescription();
    }

    public void setDescription(String var) {
        data.setDescription(var);
    }

    public BlockType getBlockType() {
        return data.getBlockType();
    }

    public void setBlockType(BlockType var) {
        data.setBlockType(var);
    }

    public String getTitle() {
        return data.getTitle();
    }

    public void setTitle(String var) {
        data.setTitle(var);
    }



    public TagType getTagType() {
        return data.getTagType();
    }

    public void setTagType(TagType tagType) {

    }

    public List<IBlock> getChildren() {
        return null;
    }

    public void setChildren(List<IBlock> list) {

    }
}
