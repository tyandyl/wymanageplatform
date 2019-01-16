package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.AttributeNameType;
import com.wy.manage.platform.core.attribute.IAttributeValue;
import com.wy.manage.platform.core.attribute.Properties;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.GUIDTools;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by tianye on 2018/7/1.
 */
public class WidgetFactory {

    public static Widget getWidget(Page page,String selectorType,String selectorValue)throws Exception{
        Widget widget=new Widget();
        widget.setCode(GUIDTools.randomUUID());
        Map<String, CssBag> cssMaps = page.getCssMaps();
        CssBag cssBag = cssMaps.get(selectorValue);
        StringBuffer str=new StringBuffer();
        str.append("<div id=\"");
        str.append(selectorValue);
        str.append("\"");
        if(cssBag!=null){
            SelectorType value = SelectorType.getSelectorType(selectorType);
            if(cssBag.getSelectorType().getCode()==value.getCode()){
                Map<String, List<String>> map = cssBag.getMap();
                if(map!=null && map.size()>0){
                    str.append(" style=\"");
                    for(String strM:AttributeNameType.getNameList()){
                        List<String> list = map.get(strM);
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
                    widget.setTitle(selectorValue);
                    str.append("\" id=\""+widget.getCode()+"\">");
                    page.getStr().append(str);

                    return widget;
                }
            }

        }
        return null;
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
