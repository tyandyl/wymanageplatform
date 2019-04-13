package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.ChinaFontTools;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class InputLineAction extends BasicAction {
    private static final String CHINESE_FONTS="ChineseFonts";
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        WidgetNode widgetNode=null;
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get("selectorValue")!=null){
                String value = IgnoreTools.ignore(regularValue.get("selectorValue").toString());
                if(regularValue!=null && regularValue.get("selectorType")!=null){
                    String s = IgnoreTools.ignore(regularValue.get("selectorType").toString());
                    Object dataFlagValue = regularValue.get("dataFlagValue");
                    Widget widget = WidgetFactory.getWidgetEx(model, s, value, TagType.INPUT,dataFlagValue);
                    widgetNode = WidgetFactory.getWidgetNode(widget,true);
                    WidgetFactory.addWidgetNode(model,widgetNode);
                }
            }else {
                Object dataFlagValue = regularValue.get("dataFlagValue");
                Widget widget = WidgetFactory.getWidgetEx(model, null, null, TagType.INPUT,dataFlagValue);
                widgetNode = WidgetFactory.getWidgetNode(widget,true);
                WidgetFactory.addWidgetNode(model,widgetNode);
            }
            //不确定的正则表达式放开头不放结尾
            Object chineseFonts = regularValue.get(CHINESE_FONTS);
            if(chineseFonts!=null){
                String s = ChinaFontTools.decodeUnicode(String.valueOf(chineseFonts));
                Map<String, String> urlContents = model.getPage().getUrlContents();
                urlContents.put(widgetNode.getCode(),s);
            }
        }
    }

    @Override
    public String getName() {
        return "inputLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("selectorType");
        list.add("selectorValue");
        list.add(CHINESE_FONTS);
        list.add("dataFlagValue");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}



