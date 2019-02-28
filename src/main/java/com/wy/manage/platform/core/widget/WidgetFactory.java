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

    private static HashMap<String, String> eventMap = new HashMap<String, String>() {
        private static final long serialVersionUID = -7130991970747133460L;
        {
            put("search_button", "move,click");
            put("window2-combo-list-div", "move");
            put("window2-combo-list-cell-after", "click");
            put("window2-combo-list-cell", "record");
            put("window2-table-input","move");
        }
    };

    public static Widget getWidget(WidgetModel model,String selectorType,String selectorValue,TagType tagType)throws Exception{
        Widget widget=new Widget();
        widget.setCode(GUIDTools.randomUUID());
        widget.setTagType(tagType);

        processEvent(selectorValue,widget);

        buildInStyleAnalyze( model, selectorType, selectorValue, widget);
        widget.setTitle(selectorValue);
        return widget;
    }


    public static void buildInStyleAnalyze(WidgetModel model,String selectorType,String selectorValue,Widget widget)throws Exception{
        Map<String, CssBag> cssMaps = model.getPage().getCssMaps();
        CssBag cssBag = cssMaps.get(selectorValue);
        SelectorType value = SelectorType.getSelectorType(selectorType);
        StringBuffer str=new StringBuffer();
        //如果value不为空，则必须类型相等；或者value为空，表示无选择器
        if((cssBag !=null && value!=null && cssBag.getSelectorType().getCode()==value.getCode())
                || cssBag==null){
            Map<String, List<String>> map = cssBag!=null?cssBag.getMap():null;
            for(String strM:AttributeNameType.getNameList()){
                List<String> list = map!=null?map.get(strM):null;
                //jValue可为top,left,position,id--插入时使用
                String jValue = model.getParamResult().getParam().get(strM);
                if(StringUtils.isNotBlank(jValue)){
                    list= new ArrayList<String>();
                    list.add(jValue);
                    model.getParamResult().getParam().remove(strM);
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
        }
        widget.setCurPros(str.toString());

    }

    public static void processEvent(String selectorValue,Widget widget){
        if(selectorValue!=null){
            String event = eventMap.get(selectorValue.trim());
            if(event!=null){
                String[] split = event.split(",");
                if(split!=null && split.length>0){
                    for(int i=0;i<split.length;i++){
                        if("move".equalsIgnoreCase(split[i])){
                            widget.setMoved(true);
                        }else if("click".equalsIgnoreCase(split[i])){
                            widget.setClick(true);
                        }else if("record".equalsIgnoreCase(split[i])){
                            widget.setRecord(true);
                        }
                    }
                }
            }
        }
    }



    public static void addWidgetNode(WidgetModel model,WidgetNode widgetNode)throws Exception {
        WidgetNodeTree widgetNodeTree = model.getPage().getWidgetNodeTree();
        WidgetNode root = widgetNodeTree.getRoot();
        if(root==null){
            widgetNodeTree.setRoot(widgetNode);
            widgetNode.setFullCode(widgetNode.getCode());
            widgetNode.setParentNode(null);
            widgetNodeTree.setNewestNoClosed(new Stack<WidgetNode>());
            widgetNodeTree.getNewestNoClosed().push(widgetNode);
            widgetNodeTree.getNodeMap().put(widgetNode.getCode(),widgetNode);
            return ;
        }
        //解决控件插入
        WidgetNode widgetNodeInsert=null;
        AddType addType = model.getParamResult().getAddType();
        if(addType==AddType.WIDGET){
            String jValue = model.getParamResult().getParam().get("id");
            if(StringUtils.isNotBlank(jValue)){
                WidgetNode widgetNode1 = widgetNodeTree.getNodeMap().get(jValue);
                if(widgetNode1!=null){
                    widgetNodeInsert=widgetNode1;
                    model.getParamResult().getParam().remove("id");
                }else {
                    ExceptionTools.ThrowException("定位参数没找到node");
                }

            }else {
                ExceptionTools.ThrowException("定位参数没找到node");
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


    public static WidgetNode getWidgetNode(Widget data,boolean isFirstClosed)throws Exception {
        WidgetNode widgetNode=new WidgetNode();
        widgetNode.setData(data);
        widgetNode.setCode(data.getCode());
        widgetNode.setFirstClosed(isFirstClosed);
        return widgetNode;
    }

}
