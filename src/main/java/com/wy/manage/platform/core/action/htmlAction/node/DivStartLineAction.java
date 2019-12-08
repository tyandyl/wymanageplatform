package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.constant.Constant;
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
public class DivStartLineAction extends BasicAction {

    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();
            Object selectorValue = regularValue.get(Constant.SELECTOR_VALUE);
            String value=null;
            if(selectorValue!=null){
                value = IgnoreTools.ignore(selectorValue.toString());
            }
            Object selectorType = regularValue.get(Constant.SELECTOR_TYPE);
            String s=null;
            if(selectorType!=null){
                s = IgnoreTools.ignore(selectorType.toString());
            }
            if(value!=null && value.equalsIgnoreCase("removeALLWY")){
                System.out.println("removeALLWY");
            }
            Object dataFlagValue = regularValue.get(Constant.DATA_FLAG_VALUE);
            Object widgetTitleValue = regularValue.get(Constant.WIDGET_TITLE_VALUE);
            Object chineseFonts = regularValue.get(Constant.CHINESE_FONTS);
            Object widgetFlagValue = regularValue.get(Constant.WIDGET_FLAG_VALUE);
            Object event_value = regularValue.get(Constant.EVENT_VALUE);



            Widget widget = WidgetFactory.getWidgetEx(model, s, value, TagType.DIV,dataFlagValue,chineseFonts,widgetFlagValue,widgetTitleValue);
            if(event_value!=null){
                model.getPage().getEventMap().put(event_value.toString(),widget.getCode());
            }
            WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
            WidgetFactory.addWidgetNode(model,widgetNode);


        }
    }

    @Override
    public String getName() {
        return "divStartLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add(Constant.SELECTOR_TYPE);
        list.add(Constant.SELECTOR_VALUE);
        list.add(Constant.CHINESE_FONTS);
        list.add(Constant.DATA_FLAG_VALUE);
        list.add(Constant.WIDGET_TITLE_VALUE);
        list.add(Constant.WIDGET_FLAG_VALUE);
        list.add(Constant.EVENT_VALUE);
        list.add(Constant.CHINESE_FONTS_LINE);
        list.add(Constant.DATA_FLAG_LINE);
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}

