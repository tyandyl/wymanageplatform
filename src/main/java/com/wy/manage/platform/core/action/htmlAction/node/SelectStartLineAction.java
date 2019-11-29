package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
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
            Object dataFlagValue = regularValue.get("dataFlagValue");
            Widget widget = WidgetFactory.getWidgetEx(model, s, value, TagType.SELECT,dataFlagValue);

            Object multipleLine = regularValue.get("multipleLine");
            if(multipleLine!=null){
                widget.setMultiple("multiple");
            }

            WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
            WidgetFactory.addWidgetNode(model,widgetNode);

            String selectShowByPage = widget.getSelectShowByPage();
            if("1".equalsIgnoreCase(selectShowByPage)){
                Map<String, String> urlContents = model.getPage().getProDataTitleMap();
                if(urlContents!=null && urlContents.size()>0){
                    for(Map.Entry<String,String> entry:urlContents.entrySet()){
                        Widget widget1 = WidgetFactory.getWidget(model, null, null, TagType.OPTION);
                        widget1.setOutValue(entry.getValue());
                        widget1.setValue(entry.getKey());
                        WidgetNode widgetNode1 = WidgetFactory.getWidgetNode(widget1,false);
                        WidgetFactory.addWidgetNode(model,widgetNode1);
                        WidgetNodeTree widgetNodeTree = model.getPage().getWidgetNodeTree();
                        //闭环校验，校验一些div名称之类的，目前先不校验
                        widgetNodeTree.getNewestNoClosed().pop();
                    }
                }
            }


        }
    }

    @Override
    public String getName() {
        return "selectStartLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList(
                SELECTOR_VALUE,
                SELECTOR_TYPE,
                this.getName(),
                "multipleLine",
                "dataFlagValue");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}


