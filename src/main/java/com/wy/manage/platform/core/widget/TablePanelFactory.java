package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.AttributeNameType;
import com.wy.manage.platform.core.attribute.Properties;
import com.wy.manage.platform.core.entrance.Context;
import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.parser.NfaStateMachine;
import com.wy.manage.platform.core.parser.NfaStateNode;
import com.wy.manage.platform.core.parser.NodeHandle;
import com.wy.manage.platform.core.utils.AtomicTools;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by tianye
 */
public class TablePanelFactory {
    public static void addTableTd(WidgetNode widgetNode,WidgetModelParamResult result,Page page){
        int i=0;
        while (widgetNode.getChildNodes().size()>0){
            i++;
            if(widgetNode.getData().getTagType()==TagType.TR){
                List<WidgetNode> childNodes = widgetNode.getChildNodes();
                if(childNodes.size()>0){
                    if(StringUtils.isBlank(childNodes.get(0).getData().getOutValue())){
                        childNodes.get(0).getData().setOutValue(result.getParam().get("fieldTitle"));
                        childNodes.get(1).getData().setDataFlag(result.getParam().get("fieldName"));

                    }else {
                        WidgetNode widgetNode1 = widgetNode.getChildNodes().get(0);
                        WidgetNode widgetNode2 = widgetNode.deepCloneWidgetNode(widgetNode1);

                        widgetNode2.getData().setOutValue(result.getParam().get("fieldTitle"));
                        widgetNode.getChildNodes().add(widgetNode2);

                        WidgetNode widgetNode3 = widgetNode.getChildNodes().get(1);
                        WidgetNode widgetNode4 = widgetNode.deepCloneWidgetNode(widgetNode3);

                        if("2".equalsIgnoreCase((result.getParam().get("fieldType")))){
                            widgetNode4.getChildNodes().clear();
                            page.getWidgetNodeTree().getNodeMap().put(widgetNode4.getData().getCode(),widgetNode4);
                            widgetNode4=createWidgetNode(page, widgetNode4.getData().getCode());
                        }else{
                            widgetNode4.getData().setDataFlag(result.getParam().get("fieldName"));
                        }


                        widgetNode.getChildNodes().add(widgetNode4);
                    }

                }
                break;
            }
            if(i==5){
                break;
            }
            widgetNode=widgetNode.getChildNodes().get(0);
        }
    }


    public static WidgetNode createWidgetNode(final Page page, final String code){
        try {
            WidgetModel modelC=new WidgetModel(){
                @Override
                public BasicModel initLoadHtmlModel() {
                    return  new HtmlModel<Page>(){
                        @Override
                        public String getRegularAddress() {
                            return "regular/widget.properties";
                        }
                        @Override
                        public String getContentAddress() {
                            return "template/combolist/combolist.html";
                        }
                    };
                }

                @Override
                public Page loadPage(WidgetModelParamResult paramResult) {
                    return page;
                }

            };
            WidgetModelParamResult paramResult=new WidgetModelParamResult();
            paramResult.getParam().put("id",code);
            paramResult.setHandleType(HandleType.NEW_WIDGET);
            modelC.setParamResult(paramResult);
            modelC.add();
        }catch (Exception e){

        }

        WidgetNode widgetNode = page.getWidgetNodeTree().getNodeMap().get(code);

        return widgetNode;

    }





}
