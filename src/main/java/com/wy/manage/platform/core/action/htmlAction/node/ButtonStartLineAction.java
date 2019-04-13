package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.utils.TempTools;
import com.wy.manage.platform.core.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class ButtonStartLineAction extends BasicAction {
    private static final String SELECTOR_VALUE="selectorValue";
    private static final String SELECTOR_TYPE="selectorType";
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();

            if(regularValue!=null && regularValue.get(SELECTOR_VALUE)!=null){
                String selectorValue = IgnoreTools.ignore(regularValue.get(SELECTOR_VALUE).toString());
                if(regularValue!=null && regularValue.get(SELECTOR_TYPE)!=null){
                    String selectorType = IgnoreTools.ignore(regularValue.get(SELECTOR_TYPE).toString());
                    Widget widget = WidgetFactory.getWidget(model, selectorType, selectorValue, TagType.BUTTON);
                    WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
                    WidgetFactory.addWidgetNode(model,widgetNode);
                }
            }else {
                Widget widget = WidgetFactory.getWidget(model, null, null, TagType.BUTTON);
                WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
                WidgetFactory.addWidgetNode(model,widgetNode);
            }
        }
    }

    @Override
    public String getName() {
        return "buttonStartLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList(
                SELECTOR_VALUE,
                SELECTOR_TYPE,
                this.getName());
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}


