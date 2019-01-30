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
        createTagType( str, tagType,widget);
        createId( str, selectorType,selectorValue);
        buildInStyle( page, selectorType, selectorValue, str, widget);
        widget.setTitle(selectorValue);
        str.append(">");
        page.getStr().append(str);
        return widget;
    }

    public static void buildInStyle(Page page,String selectorType,String selectorValue,StringBuffer str,Widget widget)throws Exception{
        createWd( str, widget);
        buildInStyleAnalyze( page, selectorType, selectorValue, str, widget);

    }

    public static void buildInStyleAnalyze(Page page,String selectorType,String selectorValue,StringBuffer str,Widget widget)throws Exception{
        Map<String, CssBag> cssMaps = page.getCssMaps();
        CssBag cssBag = cssMaps.get(selectorValue);
        SelectorType value = SelectorType.getSelectorType(selectorType);
        //如果value不为空，则必须类型相等；或者value为空，表示无选择器
        if((cssBag !=null && value!=null && cssBag.getSelectorType().getCode()==value.getCode())
                || cssBag==null){
            Map<String, List<String>> map = cssBag!=null?cssBag.getMap():null;
            str.append(" style=\"");
            for(String strM:AttributeNameType.getNameList()){
                List<String> list = map!=null?map.get(strM):null;
                if("top".equalsIgnoreCase(strM) || "left".equalsIgnoreCase(strM)){
                    String[] tops = page.getParamMap().get(strM);
                    if(tops!=null && tops.length>0 && page.getFirstIsCame()>0 && page.getFirstIsCame()<3){
                        list= Arrays.asList(tops);
                        int firstIsCame = page.getFirstIsCame();
                        page.setFirstIsCame(++firstIsCame);
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


    public static void createTagType(StringBuffer str,TagType tagType,Widget widget){
        str.append("<"+tagType.getName());
        widget.setTagType(tagType);
    }

    public static void createId(StringBuffer str,String selectorType,String selectorValue){
//        if(StringUtils.isNotBlank(selectorType)){
//            str.append(" "+selectorType+"='");
//            str.append(selectorValue+"' ");
//        }
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
            widgetNodeTree.getNodeMap().put(widgetNode.getCode(),widgetNode);
            return ;
        }
        //解决控件插入
        WidgetNode widgetNodeInsert=null;
        String[] ids = page.getParamMap().get("id");
        if(ids!=null && ids.length>0){
            for(String id:ids){
                if(StringUtils.isNotBlank(id)){
                    WidgetNode widgetNode1 = widgetNodeTree.getNodeMap().get(id);
                    if(widgetNode1!=null){
                        widgetNodeInsert=widgetNode1;
                        break;
                    }
                }
            }
        }
        if(widgetNodeInsert!=null){
            widgetNode.setFullCode(widgetNodeInsert.getFullCode()+widgetNode.getCode());
            widgetNode.setParentNode(widgetNodeInsert);
            widgetNodeInsert.getChildNodes().add(widgetNode);
            widgetNodeTree.getNodeMap().put(widgetNode.getCode(),widgetNode);
            Stack<WidgetNode> newestNoClosed = widgetNodeTree.getNewestNoClosed();
            newestNoClosed.push(widgetNode);
        }else {
            //这里都是开口的
            Stack<WidgetNode> newestNoClosed = widgetNodeTree.getNewestNoClosed();
            if(newestNoClosed.empty()){
                ExceptionTools.ThrowException("开口元素为空");
            }
            WidgetNode peek = newestNoClosed.peek();
            widgetNode.setFullCode(peek.getFullCode()+widgetNode.getCode());
            widgetNode.setParentNode(peek);
            //判断是不是首个标签闭合如<input />
            if(!widgetNode.isFirstClosed()){
                newestNoClosed.push(widgetNode);
            }
            peek.getChildNodes().add(widgetNode);
            widgetNodeTree.getNodeMap().put(widgetNode.getCode(),widgetNode);
        }


    }

    public static WidgetNode getWidgetNode(Widget data)throws Exception {
        WidgetNode widgetNode=new WidgetNode();
        widgetNode.setData(data);
        widgetNode.setCode(data.getCode());
        return widgetNode;
    }

    public static WidgetNode getWidgetNode(Widget data,boolean isFirstClosed)throws Exception {
        WidgetNode widgetNode=new WidgetNode();
        widgetNode.setData(data);
        widgetNode.setCode(data.getCode());
        widgetNode.setFirstClosed(isFirstClosed);
        return widgetNode;
    }

}
