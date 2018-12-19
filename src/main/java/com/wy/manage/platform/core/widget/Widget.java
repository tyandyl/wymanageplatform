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
    private Properties properties;

    private IFlow flow;

    private String description;

    private BlockType blockType;

    private String title;

    private TagType tagType;

    public Widget(){
        if(properties==null){
            properties=new Properties();
        }
        Map<AttributeNameType, IAttributeValue> map = properties.getProperties();
        if(map==null){
            map=new HashMap<AttributeNameType, IAttributeValue>();
        }
    }


    /**
     * 获取宽度
     * @return
     * @throws Exception
     */
    public String getWidth() throws Exception {
        if(properties==null){
            properties=new Properties();
        }
        IAttributeValue var = properties.getAttributeValue(AttributeNameType.WIDTH);
        return var!=null?var.getAttributeValue():null;
    }

    /**
     * 设置宽度
     * @param var
     * @throws Exception
     */
    public void setWidth(String var) throws Exception {
        setPropertyValue(AttributeNameType.WIDTH,
                var,
                SelectorType.ID_SELECTOR,
                false,
                StyleSheetType.EXTERNAL,
                "id");
    }


    /**
     * 设置属性值
     * @param var0 属性名
     * @param var1 属性值
     * @param var2 选择器类型
     * @param var3 值是否是关键字
     * @param var4 样式表类型
     * @param var5 选择器名称
     * @throws Exception
     */
    public void setPropertyValue(AttributeNameType var0,String var1,SelectorType var2,boolean var3,StyleSheetType var4,String var5)throws Exception {
        if(properties==null){
            properties=new Properties();
        }
        //获取属性值
        IAttributeValue attributeValue = properties.getAttributeValue(var0);
        if(attributeValue==null){
            attributeValue =new AttributeValue();
        }

        attributeValue.setAttributeValue(var1);
        attributeValue.setSelectorType(var2);
        attributeValue.setValueIsKeyWord(var3);
        attributeValue.setStyleSheetType(var4);

        //设置id或者class，默认是id
        if(StringUtils.isBlank(var5)){
            String uuid=null;
            //如果不存在的话，取当前控件默认的值
            if(properties.getProperties().size()>0){
                int i=0;
                do{
                    SelectorType selectorType = properties.getProperties().get(i).getSelectorType();
                    if(selectorType==SelectorType.ID_SELECTOR
                            || selectorType==SelectorType.CLASS_SELECTOR){
                        uuid=properties.getProperties().get(i).getSelectorName();
                        attributeValue.setSelectorType(selectorType);
                        break;
                    }
                    i++;
                }while (i<properties.getProperties().size());
            }
            attributeValue.setSelectorName(uuid!=null?uuid:GUIDTools.randomUUID());
        }else {
            attributeValue.setSelectorName(var5);
        }
        properties.getProperties().put(var0,attributeValue);
    }


    public String getHeight() throws Exception {
        IAttributeValue var = properties.getAttributeValue(AttributeNameType.HEIGHT);
        return var!=null?var.getAttributeValue():null;
    }

    public void setHeight(String var) throws Exception {
        setPropertyValue(AttributeNameType.HEIGHT,var,SelectorType.ID_SELECTOR,false,StyleSheetType.EXTERNAL,"id");
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

}
