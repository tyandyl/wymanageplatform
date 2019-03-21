package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.parser.NfaStateMachine;
import com.wy.manage.platform.core.parser.NfaStateNode;
import com.wy.manage.platform.core.parser.NodeHandle;
import com.wy.manage.platform.core.utils.AtomicTools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by tianye
 */
public class TablePanelFactory {
    public static void addTableTd(WidgetNode widgetNode,WidgetNode widgetNodeEx,List<CurWidget> curWidgets,WidgetModel model){
        int i=0;
        while (widgetNode.getChildNodes().size()>0){
            i++;
            if(widgetNode.getData().getTagType()==TagType.TR){
                List<WidgetNode> childNodes = widgetNode.getChildNodes();
                if(childNodes.size()>0){
                    childNodes.get(0).getData().setOutValue("666");
                    WidgetNode widgetNode1 = widgetNode.getChildNodes().get(0);
                    WidgetNode widgetNode2 = widgetNode.deepCloneWidgetNode(widgetNode1);
                    WidgetNode widgetNode3 = widgetNode.getChildNodes().get(1);
                    WidgetNode widgetNode4 = widgetNode.deepCloneWidgetNode(widgetNode3);
                    widgetNode.getChildNodes().add(widgetNode2);
                    widgetNode.getChildNodes().add(widgetNode4);
                    model.getPage().fillNodeEX(widgetNodeEx,curWidgets);
                }
                break;
            }
            if(i==5){
                break;
            }
            widgetNode=widgetNode.getChildNodes().get(0);
        }
    }





}
