package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.AttributeNameType;
import com.wy.manage.platform.core.attribute.IAttributeValue;
import com.wy.manage.platform.core.attribute.Properties;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.GUIDTools;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by tianye on 2018/7/1.
 */
public class WidgetFactory {

    public static Widget getWidget(Page page,String selectorType,String selectorValue,TagType tagType)throws Exception{
        Widget widget=new Widget();
        widget.setCode(GUIDTools.randomUUID());
        StringBuffer str=new StringBuffer();
        createTagType( str, tagType);
        createId( str, selectorType,selectorValue);
        buildInStyle( page, selectorType, selectorValue, str, widget);
        widget.setTitle(selectorValue);
        str.append(">");
        page.getStr().append(str);
        return widget;
    }

    public static void buildInStyle(Page page,String selectorType,String selectorValue,StringBuffer str,Widget widget)throws Exception{
        if("4".equalsIgnoreCase(page.getStyleType())){
            if(!page.isFirstIsCame()){
                createWd( str, widget);
                String[] tops = page.getParamMap().get("top");
                String[] lefts = page.getParamMap().get("left");
                str.append(" style=\"position:absolute;" +
                        "top:" +tops[0]+"px;"+
                        "left:" +lefts[0]+"px;"+
                        "width:350px;" +
                        "height:400px;" +
                        "background: #f9836b;\"");
                page.setFirstIsCame(true);
            }

        }else {
            createWd( str, widget);
            buildInStyleAnalyze( page, selectorType, selectorValue, str, widget);
        }

    }

    public static void buildInStyleAnalyze(Page page,String selectorType,String selectorValue,StringBuffer str,Widget widget)throws Exception{
        Map<String, CssBag> cssMaps = page.getCssMaps();
        CssBag cssBag = cssMaps.get(selectorValue);
        if(cssBag!=null){
            SelectorType value = SelectorType.getSelectorType(selectorType);
            if(cssBag.getSelectorType().getCode()==value.getCode()){
                Map<String, List<String>> map = cssBag.getMap();
                if(map!=null && map.size()>0){
                    str.append(" style=\"");
                    for(String strM:AttributeNameType.getNameList()){
                        List<String> list = map.get(strM);
                        if("top".equalsIgnoreCase(strM) || "left".equalsIgnoreCase(strM)){
                            String[] tops = page.getParamMap().get(strM);
                            if(tops!=null && tops.length>0){
                                list= Arrays.asList(tops);
                            }
                        }
                        if(list!=null && list.size()>0){
                            AttributeNameType attributeNameType = AttributeNameType.getAttributeNameType(strM);
                            if(attributeNameType!=null){
                                widget.setProperty(attributeNameType,list,value,selectorValue);
                                for(String m:list){
                                    str.append(attributeNameType.getName());
                                    str.append(":");
                                    str.append(m);
                                    str.append(";");
                                }
                            }
                        }
                    }
                    str.append("\"");
                }
            }

        }
    }


    public static void createTagType(StringBuffer str,TagType tagType){
        str.append("<"+tagType.getName());
    }

    public static void createId(StringBuffer str,String selectorType,String selectorValue){
        if(StringUtils.isNotBlank(selectorType)){
            str.append(" "+selectorType+"='");
            str.append(selectorValue+"' ");
        }
    }
    public static void createWd(StringBuffer str,Widget widget){
        str.append(" wd='");
        str.append(widget.getCode());
        str.append("'");
    }


    public static void addWidgetNode(Page page,WidgetNode widgetNode)throws Exception {
        WidgetNodeTree widgetNodeTree = page.getWidgetNodeTree();
        WidgetNode root = widgetNodeTree.getRoot();
        if(root==null){
            widgetNodeTree.setRoot(widgetNode);
            widgetNode.setFullCode(widgetNode.getCode());
            widgetNode.setParentNode(null);
            widgetNodeTree.getNewestNoClosed().push(widgetNode);
            return ;
        }
        //如果是闭环
        if(widgetNode.isClosed()){
            //闭环校验，校验一些div名称之类的，目前先不校验
            widgetNodeTree.getNewestNoClosed().pop();
        }else {
            Stack<WidgetNode> newestNoClosed = widgetNodeTree.getNewestNoClosed();
            WidgetNode peek = newestNoClosed.peek();
            widgetNode.setFullCode(peek.getFullCode()+widgetNode.getCode());
            widgetNode.setParentNode(peek);
            newestNoClosed.push(widgetNode);
            peek.getChildNodes().add(widgetNode);
        }

    }

    public static WidgetNode getWidgetNode(Widget data,boolean isClosed)throws Exception {
        WidgetNode widgetNode=new WidgetNode();
        widgetNode.setData(data);
        widgetNode.setCode(data.getCode());
        widgetNode.setClosed(isClosed);
        return widgetNode;
    }

}
