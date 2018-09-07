package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.Properties;

import java.util.List;

/**
 * Created by tianye on 2018/9/5.
 */
public class Div implements IWidget{

    private IWidget iWidget;

    public Div(IWidget iWidget){
        this.iWidget=iWidget;
    }

    public String getWidth() throws Exception {
        return iWidget.getWidth();
    }

    public void setWidth(String var) throws Exception {
        iWidget.setWidth(var);
    }

    public String getHeight() throws Exception {
        return iWidget.getHeight();
    }

    public void setHeight(String var) throws Exception {
        iWidget.setHeight(var);
    }

    public IFlow getFlow() {
        return iWidget.getFlow();
    }

    public void setFlow(IFlow var) {
        iWidget.setFlow(var);
    }

    public String getDescription() {
        return iWidget.getDescription();
    }

    public void setDescription(String var) {
        iWidget.setDescription(var);
    }

    public BlockType getBlockType() {
        return iWidget.getBlockType();
    }

    public void setBlockType(BlockType var) {
        iWidget.setBlockType(var);
    }

    public String getTitle() {
        return iWidget.getTitle();
    }

    public void setTitle(String var) {
        iWidget.setTitle(var);
    }

    public List<String> getIds() {
        return iWidget.getIds();
    }

    public void setIds(List<String> list) {
        iWidget.setIds(list);
    }

    public IWidget addSelector(StyleSheetType styleSheetType, String SelectorName) {
        return null;
    }

    public List<String> getClasses() {
        return iWidget.getClasses();
    }

    public void setClasses(List<String> list) {
        iWidget.setClasses(list);
    }

    public Properties getProperties() {
        return iWidget.getProperties();
    }

    public void setProperties(Properties properties) {
        iWidget.setProperties(properties);
    }

    public TagType getTagType() {
        return iWidget.getTagType();
    }

    public void setTagType(TagType tagType) {
        iWidget.setTagType(tagType);
    }

    public List<IBlock> getChildren() {
        return iWidget.getChildren();
    }

    public void setChildren(List<IBlock> list) {
        iWidget.setChildren(list);
    }

    public void invoke() throws Exception {


    }
}
