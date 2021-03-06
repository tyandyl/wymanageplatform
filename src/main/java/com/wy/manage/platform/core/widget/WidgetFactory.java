package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.AttributeNameType;
import com.wy.manage.platform.core.bean.*;
import com.wy.manage.platform.core.bean.Result;
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

    public static Widget getWidgetEx(WidgetModel model,String selectorType,String selectorValue,TagType tagType,Object dataFlagValue)throws Exception{
        Widget widget=new Widget();
        widget.setCode(GUIDTools.randomUUID());
        widget.setTagType(tagType);
        if(dataFlagValue!=null){
            widget.setDataFlag(dataFlagValue.toString());
        }
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
            if(selectorValue.equalsIgnoreCase("selectMultipleRight")){
                System.out.println("");
            }
            List<RegisterEventManage> manages = model.getParamResult().getRegisterEvent().handle().getMapManage().get(selectorValue.trim());
            if(manages!=null && manages.size()>0){
                for(RegisterEventManage manage:manages){
                    manage.setWidget(widget);
                    checkArray( manage,model);
                }

            }
            List<RegisterEventData> registerEventDatas = model.getParamResult().getRegisterEvent().handle().getMaps().get(selectorValue.trim());
            if(registerEventDatas!=null && registerEventDatas.size()>0){
                for(RegisterEventData registerEventData:registerEventDatas){
                    if(registerEventData!=null){
                        List<RegisterEventManage> manage1s = model.getParamResult().getRegisterEvent().handle().getMapManage().get(registerEventData.getSelectorValue());
                        if(manage1s!=null && manage1s.size()>0){
                            for(RegisterEventManage manage1:manage1s){
                                manage1.getArr()[registerEventData.getI()]=widget.getCode();
                                checkArray( manage1,model);
                            }
                        }

                    }
                }
            }

        }
    }

    public static void checkArray(RegisterEventManage manage,WidgetModel model){
        int paramNum = manage.getParamNum();
        //不减一的原因是第一个参数是事件
        if(paramNum>0 && manage.getArr()[paramNum]!=null){
            boolean isB=false;
            for(int i=paramNum-1;i>0;i--){
                if(manage.getArr()[i]==null){
                    isB=true;
                }
            }
            if(!isB){
                Widget widget = manage.getWidget();
                String[] arr = manage.getArr();
                StringBuffer str=new StringBuffer();
                for(int i=0;i<=paramNum;i++){
                    str.append(arr[i]);
                    if(i!=paramNum){
                        str.append(",");
                    }
                }
                if(widget!=null){
                    widget.getRegisterParam().getRegister().add(str.toString());
                    if(manage.getArr()[0].equalsIgnoreCase("requestClick")){
                        Map<String, WidgetNode> nodeMap = model.getPage().getWidgetNodeTree().getNodeMap();
                        if(manage.getArr()[1]!=null){
                            WidgetNode widgetNode = nodeMap.get(manage.getArr()[1]);
                            widget.getRegisterParam().getRequestParam().add(manage.getArr()[1]+":"+widgetNode.getData().getTagType().getName()+":"+widgetNode.getData().getDataFlag());
                        }
                        if(manage.getArr()[2]!=null){
                            WidgetNode widgetNode = nodeMap.get(manage.getArr()[2]);
                            widget.getRegisterParam().getRequestParam().add(manage.getArr()[2]+":"+widgetNode.getData().getTagType().getName()+":"+widgetNode.getData().getDataFlag());
                        }
                        if(manage.getArr()[3]!=null){
                            WidgetNode widgetNode = nodeMap.get(manage.getArr()[3]);
                            widget.getRegisterParam().getRequestParam().add(manage.getArr()[3]+":"+widgetNode.getData().getTagType().getName()+":"+widgetNode.getData().getDataFlag());
                        }

                    }
                    manage.getArr()[1]=null;
                    manage.getArr()[2]=null;
                    manage.getArr()[3]=null;
                }
            }


        }
        if(manage.getArr()[0].equalsIgnoreCase("widget")){
            Widget widget = manage.getWidget();
            if(widget!=null){
                widget.setFlag(true);

                String targetId = model.getParamResult().getParam().get("targetId");
                if(targetId!=null){
                    String[] split = targetId.split(",");
                    for(String m:split){
                        widget.getRegisterParam().getTargetParam().add(m);
                    }
                }

                Integer integer = RegisterEvent.getWidgetMap().get(manage.getSelectorValue());
                if(integer!=null){
                    widget.setBlockType(BlockType.getBlockType(integer));

                }

            }
        }

        if(manage.getArr()[0].equalsIgnoreCase("selectShowByPage")){
            Widget widget = manage.getWidget();
            if(widget!=null){
                widget.setSelectShowByPage("1");
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
        HandleType handleType = model.getParamResult().getHandleType();
        if(handleType == HandleType.NEW_WIDGET){
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
            if(handleType == HandleType.NEW_WIDGET){
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
        curWidget.setRegisterParam(data.getRegisterParam());
        curWidget.setCurPros(data.getCurPros());
        curWidget.setOutContentValue(data.getOutValue());
        curWidget.setFlag(data.isFlag());
        curWidget.setWidgetName(data.getBlockType()!=null?data.getBlockType().getName():null);
        curWidget.setMultiple(data.getMultiple());
        curWidget.setValue(data.getValue());
        curWidget.setUrl(data.getUrl());
        curWidget.setUrlIsDefault(data.isUrlIsDefault());
        curWidget.setDataFlag(data.getDataFlag());
        Result result = model.getParamResult().getResult();
        Object result1 = result.getResult();
        if(result1==null){
            List<CurWidget> m=new ArrayList<CurWidget>();
            m.add(curWidget);
            result.setResult(m);
        }else {
            if(result1 instanceof List){
                ((List<CurWidget>)result1).add(curWidget);
            }
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
