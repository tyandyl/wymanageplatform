package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.AttributeNameType;
import com.wy.manage.platform.core.attribute.AttributeValue;
import com.wy.manage.platform.core.attribute.IAttributeValue;
import com.wy.manage.platform.core.attribute.Properties;
import com.wy.manage.platform.core.utils.GUIDTools;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/29.
 */
public class Widget implements IWidget, Serializable {

    private static final long serialVersionUID = -47188272587767738L;
    private Properties properties=new Properties();

    private IFlow flow;

    private String description;

    private BlockType blockType;

    private String title;

    private TagType tagType;

    private String code;

    private boolean isMoved=false;

    private boolean isClick=false;

    private boolean isRecord=false;

    private boolean isRecord2=false;

    private String curPros;

    /**
     * 获取宽度
     * @return
     * @throws Exception
     */
    public String getWidth() throws Exception {
        IAttributeValue var = properties.getAttributeValue(AttributeNameType.WIDTH);
        if(var!=null
                && var.getAttributeValue()!=null
                && var.getAttributeValue().size()>0){
            return var.getAttributeValue().get(0);
        }
        return null;
    }

    /**
     * 设置宽度
     * @param var
     * @throws Exception
     */
    public void setWidth(String var) throws Exception {
    }


    public void setProperty(AttributeNameType attributeNameType,
                            List<String> attributeValues,
                            SelectorType selectorType,
                            String selectorValue)throws Exception {
        if(properties==null){
            properties=new Properties();
        }
        //获取属性值
        IAttributeValue attributeValue = properties.getAttributeValue(attributeNameType);
        if(attributeValue==null){
            attributeValue =new AttributeValue();
        }

        attributeValue.setAttributeValue(attributeValues);
        attributeValue.setSelectorType(selectorType);
        attributeValue.setSelectorName(selectorValue);

        properties.getProperties().put(attributeNameType,attributeValue);
    }


    public String getHeight() throws Exception {
        IAttributeValue var = properties.getAttributeValue(AttributeNameType.HEIGHT);
        if(var!=null
                && var.getAttributeValue()!=null
                && var.getAttributeValue().size()>0){
            return var.getAttributeValue().get(0);
        }
        return null;
    }

    public void setHeight(String var) throws Exception {
    }

    public IFlow getFlow() {
        return flow;
    }

    public void setFlow(IFlow var) {
        this.flow=var;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String var) {
        this.description=var;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType var) {
        this.blockType=var;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String var) {
        this.title=var;
    }

    public List<String> getIds() {
        return null;
    }

    public void setIds(List<String> list) {

    }

    public IWidget addSelector(StyleSheetType styleSheetType, String SelectorName) {
        return null;
    }

    public List<String> getClasses() {
        return null;
    }

    public void setClasses(List<String> list) {

    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties=properties;
    }

    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType=tagType;
    }

    public void invoke() throws Exception {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public boolean isRecord() {
        return isRecord;
    }

    public void setRecord(boolean record) {
        isRecord = record;
    }

    public String getCurPros() {
        return curPros;
    }

    public void setCurPros(String curPros) {
        this.curPros = curPros;
    }

    public boolean isRecord2() {
        return isRecord2;
    }

    public void setRecord2(boolean record2) {
        isRecord2 = record2;
    }
}
