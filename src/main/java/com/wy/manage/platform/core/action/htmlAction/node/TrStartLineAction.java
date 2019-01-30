package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class TrStartLineAction extends BasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof Page) {
            Page page = (Page) t;
            Map regularValue = modelParam.getRegularValue();

            if(regularValue!=null && regularValue.get("selectorValue")!=null){
                String value = IgnoreTools.ignore(regularValue.get("selectorValue").toString());
                if(regularValue!=null && regularValue.get("selectorType")!=null){
                    String s = IgnoreTools.ignore(regularValue.get("selectorType").toString());
                    Widget widget = WidgetFactory.getWidget(page, s, value, TagType.TR);
                    WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget);
                    WidgetFactory.addWidgetNode(page,widgetNode);
                }
            }else {
                Widget widget = WidgetFactory.getWidget(page, null, null, TagType.TR);
                WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget);
                WidgetFactory.addWidgetNode(page,widgetNode);
            }
        }
    }

    @Override
    public String getName() {
        return "trStartLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("selectorType");
        list.add("selectorValue");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}



