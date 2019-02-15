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

    public static Widget getWidget(Page page,String selectorType,String selectorValue,TagType tagType)throws Exception{
        Widget widget=new Widget();
        widget.setCode(GUIDTools.randomUUID());
        StringBuffer str=new StringBuffer();
        createTagType( str, tagType,widget);
        processId( str, selectorType,selectorValue,page,widget);
        //解决控件插入
        if(page.getFirstIsCame()==3){
            page.setParamMap(new HashMap<String, String[]>());
        }
        buildInStyle( page, selectorType, selectorValue, str, widget);
        widget.setTitle(selectorValue);
        str.append(">");
        page.getStr().append(str);
        //非外层的不可以移动
        if(page.getFirstIsCame()>0){
            widget.setRemovable(false);
        }
        return widget;
    }

    public static void buildInStyle(Page page,String selectorType,String selectorValue,StringBuffer str,Widget widget)throws Exception{
        createWd( str, widget,page);
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
                Map<String, String[]> paramMap = page.getParamMap();
                String[] blocktypes = paramMap != null ? paramMap.get("blocktype") : null;

                if(list==null
                        && "position".equalsIgnoreCase(strM)
                        && blocktypes!=null
                        && blocktypes.length==1
                        && "4".equalsIgnoreCase(blocktypes[0])){
                    list=new ArrayList<String>();
                    list.add("absolute");
                    paramMap.remove("blocktype");
                    page.setParamMap(paramMap);
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

    public static void processId(StringBuffer str,String selectorType,String selectorValue,Page page,Widget widget){
//        if(StringUtils.isNotBlank(selectorType)){
//            str.append(" "+selectorType+"='");
//            str.append(selectorValue+"' ");
//        }
        if(selectorValue!=null){
            String event = eventMap.get(selectorValue.trim());
            if(event!=null){
                String[] split = event.split(",");
                if(split!=null && split.length>0){
                    for(int i=0;i<split.length;i++){
                        if("move".equalsIgnoreCase(split[i])){
                            page.setMoveWd(widget.getCode());
                            page.setMoveWdName(widget.getTagType().getName());
                        }else if("click".equalsIgnoreCase(split[i])){
                            page.setClickWd(widget.getCode());
                            page.setClickWdName(widget.getTagType().getName());
                        }else if("record".equalsIgnoreCase(split[i])){
                            page.setRecordWd(widget.getCode());
                            page.setRecordWdName(widget.getTagType().getName());
                        }
                    }
                }
            }
        }
    }
    public static void createWd(StringBuffer str,Widget widget,Page page){
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
