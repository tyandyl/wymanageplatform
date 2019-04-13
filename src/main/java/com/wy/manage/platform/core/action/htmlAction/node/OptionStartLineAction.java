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
public class OptionStartLineAction extends BasicAction {

    private static final String SELECTOR_VALUE="selectorValue";

    private static final String SELECTOR_TYPE="selectorType";

    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();
            Object selectorValue = regularValue.get(SELECTOR_VALUE);
            String value=null;
            Widget widget = WidgetFactory.getWidget(model, null, null, TagType.OPTION);
            if(selectorValue!=null){
                value = IgnoreTools.ignore(selectorValue.toString());
                widget.setValue(value);
            }
            WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
            WidgetFactory.addWidgetNode(model,widgetNode);
        }
    }

    @Override
    public String getName() {
        return "optionStartLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("selectorValue");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}


