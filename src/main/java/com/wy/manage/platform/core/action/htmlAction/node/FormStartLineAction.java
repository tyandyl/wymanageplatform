package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.utils.TempTools;
import com.wy.manage.platform.core.widget.*;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye13 on 2019/3/4.
 */
public class FormStartLineAction extends BasicAction {
    private static final String SELECTOR_VALUE="selectorValue";
    private static final String SELECTOR_TYPE="selectorType";
    @Override
    public void action(ModelParam modelParam) throws Exception {
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
            Widget widget = WidgetFactory.getWidget(model, s, value, TagType.FORM);
            WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
            WidgetFactory.addWidgetNode(model,widgetNode);
        }
    }

    @Override
    public String getName() {
        return "formStartLine";
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
