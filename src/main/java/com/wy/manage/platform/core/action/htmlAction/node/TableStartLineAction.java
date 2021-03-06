package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class TableStartLineAction extends BasicAction {
    //谷歌：如果前一位是legend top:30px;
    //火狐：如果前一位是legend top:10px;
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();

            if(regularValue!=null && regularValue.get("selectorValue")!=null){
                String value = IgnoreTools.ignore(regularValue.get("selectorValue").toString());
                if(regularValue!=null && regularValue.get("selectorType")!=null){
                    String s = IgnoreTools.ignore(regularValue.get("selectorType").toString());
                    Widget widget = WidgetFactory.getWidget(model, s, value, TagType.TABLE);
                    WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
                    WidgetFactory.addWidgetNode(model,widgetNode);
                }
            }else {
                Widget widget = WidgetFactory.getWidget(model, null, null, TagType.TABLE);
                WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
                WidgetFactory.addWidgetNode(model,widgetNode);
            }

        }
    }

    @Override
    public String getName() {
        return "tableStartLine";
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



