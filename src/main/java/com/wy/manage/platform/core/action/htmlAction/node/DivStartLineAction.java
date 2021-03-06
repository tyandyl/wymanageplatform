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
public class DivStartLineAction extends BasicAction {

    private static final String SELECTOR_VALUE="selectorValue";

    private static final String SELECTOR_TYPE="selectorType";

    private static final String CHINESE_FONTS="ChineseFonts";

    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();
            Object selectorValue = regularValue.get(SELECTOR_VALUE);
            String value=null;
            if(selectorValue!=null){
                value = IgnoreTools.ignore(selectorValue.toString());
            }
            Object selectorType = regularValue.get(SELECTOR_TYPE);
            String s=null;
            if(selectorType!=null){
                s = IgnoreTools.ignore(selectorType.toString());
            }
            if(value!=null && value.equalsIgnoreCase("removeALLWY")){
                System.out.println("removeALLWY");
            }
            Object dataFlagValue = regularValue.get("dataFlagValue");
            Widget widget = WidgetFactory.getWidgetEx(model, s, value, TagType.DIV,dataFlagValue);
            WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
            WidgetFactory.addWidgetNode(model,widgetNode);

            Object chineseFonts = regularValue.get(CHINESE_FONTS);
            if(chineseFonts!=null){
                String s1 = ChinaFontTools.decodeUnicode(String.valueOf(chineseFonts));
                Map<String, String> urlContents = model.getPage().getUrlContents();
                urlContents.put(widgetNode.getCode(),s1);
            }

        }
    }

    @Override
    public String getName() {
        return "divStartLine";
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

