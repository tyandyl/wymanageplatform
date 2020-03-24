package com.wy.manage.platform.core.widget;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by tianye
 */
public class TableListFactory {

    public static void addTableTr(WidgetNode widgetNode){
        int i=0;
        while (widgetNode.getChildNodes().size()>0){
            i++;
            if(widgetNode.getData().getTagType()==TagType.TR){
                List<WidgetNode> childNodes = widgetNode.getParentNode().getChildNodes();
                WidgetNode widgetNode1 = widgetNode.deepCloneWidgetNode(childNodes.get(childNodes.size()-1));
                List<WidgetNode> childNodes1 = widgetNode1.getChildNodes();
                if(childNodes1.size()>0){
                    for(WidgetNode node:childNodes1){
                        if(StringUtils.isBlank(node.getData().getOutValue())){
                            node.getData().setOutValue("666");
                        }
                    }
                }
                childNodes.add(widgetNode1);
                break;
            }
            if(i==5){
                break;
            }
            widgetNode=widgetNode.getChildNodes().get(0);
        }
    }


}
