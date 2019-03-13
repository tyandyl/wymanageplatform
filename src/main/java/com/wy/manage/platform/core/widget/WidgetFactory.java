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



    public static Widget getWidget(WidgetModel model,String selectorType,String selectorValue,TagType tagType)throws Exception{
        Widget widget=new Widget();
        widget.setCode(GUIDTools.randomUUID());
        widget.setTagType(tagType);

        processEvent(selectorValue,widget,model);

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

    public static void processEvent(String selectorValue,Widget widget,WidgetModel model)throws Exception{
        if(selectorValue!=null){
            RegisterEventManage manage = model.getParamResult().getRegisterEvent().handle().getMapManage().get(selectorValue.trim());
            if(manage!=null){
                manage.setWidget(widget);
            }else {
                RegisterEventData registerEventData = model.getParamResult().getRegisterEvent().handle().getMaps().get(selectorValue.trim());
                if(registerEventData!=null){
                    RegisterEventManage manage1 = model.getParamResult().getRegisterEvent().handle().getMapManage().get(registerEventData.getSelectorValue());
                    manage1.getArr()[registerEventData.getI()]=widget.getCode();
                }
            }
        }
    }

    public void checkArray(RegisterEventManage manage){
        int paramNum = manage.getParamNum();
        if(manage.getArr()[paramNum-1]!=null){


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

            }
        }

        if(widgetNodeInsert!=null){
            widgetNode.setFullCode(widgetNodeInsert.getFullCode()+widgetNode.getCode());
            widgetNode.setParentNode(widgetNodeInsert);
            widgetNodeInsert.getChildNodes().add(widgetNode);
            widgetNodeTree.getNodeMap().put(widgetNode.getCode(),widgetNode);
            Stack<WidgetNode> newestNoClosed = widgetNodeTree.getNewestNoClosed();
            newestNoClosed.push(widgetNode);
            setHandleCurWidget( model, widgetNode,widgetNodeInsert.getCode(),widgetNodeInsert.getData().getTagType().getName());
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
            if(addType==AddType.WIDGET){
                setHandleCurWidget( model, widgetNode,peek.getCode(),peek.getData().getTagType().getName());
            }
        }

    }

    public static void setHandleCurWidget(WidgetModel model,WidgetNode widgetNode,String parentWd,String parentTagName){
        Widget data = widgetNode.getData();

        CurWidget curWidget=new CurWidget();
        curWidget.setCurWd(widgetNode.getCode());
        curWidget.setCurTagName(data.getTagType().getName());
        curWidget.setParentWd(parentWd);
        curWidget.setParentTagName(parentTagName);
        curWidget.setMoved(data.isMoved());
        curWidget.setClicked(data.isClick());
        curWidget.setRecorded(data.isRecord());
        curWidget.setRecorded2(data.isRecord2());
        curWidget.setCurPros(data.getCurPros());
        curWidget.setOutContentValue(data.getOutValue());
        model.getParamResult().getCurWidgets().add(curWidget);
    }


    public static WidgetNode getWidgetNode(Widget data,boolean isFirstClosed)throws Exception {
        WidgetNode widgetNode=new WidgetNode();
        widgetNode.setData(data);
        widgetNode.setCode(data.getCode());
        widgetNode.setFirstClosed(isFirstClosed);
        return widgetNode;
    }

}
