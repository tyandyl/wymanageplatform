package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.constant.Constant;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.utils.TempTools;
import com.wy.manage.platform.core.widget.*;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye13 on 2019/3/29.
 */
public class SelectStartLineAction  extends BasicAction {

    @Override
    public void action(ModelParam modelParam) throws Exception {
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
            Object event_value = regularValue.get(Constant.EVENT_VALUE);

            Object dataFlagValue = regularValue.get(Constant.DATA_FLAG_VALUE);
            Widget widget = WidgetFactory.getWidgetEx(model, s, value, TagType.SELECT,dataFlagValue,null,null,null);

            Object multipleLine = regularValue.get(Constant.MULTIPLE_LINE);
            if(multipleLine!=null){
                widget.setMultiple("multiple");
            }
            if(event_value!=null){
                model.getPage().getEventMap().put(event_value.toString(),widget.getCode());
            }

            WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
            WidgetFactory.addWidgetNode(model,widgetNode);


        }
    }

    @Override
    public String getName() {
        return "selectStartLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList(
                Constant.SELECTOR_VALUE,
                Constant.SELECTOR_TYPE,
                this.getName(),
                Constant.MULTIPLE_LINE);
        list.add(Constant.EVENT_VALUE);
        list.add(Constant.DATA_FLAG_VALUE);
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}


