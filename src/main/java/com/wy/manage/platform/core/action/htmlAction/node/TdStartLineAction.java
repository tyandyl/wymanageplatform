package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.constant.Constant;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.ChinaFontTools;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.utils.TempTools;
import com.wy.manage.platform.core.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class TdStartLineAction extends BasicAction {
    private static final String SELECTOR_VALUE="selectorValue";
    private static final String SELECTOR_TYPE="selectorType";
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();

            if(regularValue!=null && regularValue.get(SELECTOR_VALUE)!=null){
                String value = IgnoreTools.ignore(regularValue.get(SELECTOR_VALUE).toString());
                if(regularValue!=null && regularValue.get(SELECTOR_TYPE)!=null){
                    String s = IgnoreTools.ignore(regularValue.get(SELECTOR_TYPE).toString());
                    Object event_value = regularValue.get(Constant.EVENT_VALUE);
                    Object dataFlagValue = regularValue.get(Constant.DATA_FLAG_VALUE);
                    Object chineseFonts = regularValue.get(Constant.CHINESE_FONTS);
                    Widget widget = WidgetFactory.getWidgetEx(model, s, value, TagType.TD,dataFlagValue,chineseFonts,null,null);

                    if(event_value!=null){
                        model.getPage().getEventMap().put(event_value.toString(),widget.getCode());
                    }

                    WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
                    WidgetFactory.addWidgetNode(model,widgetNode);
                }
            }else {
                Widget widget = WidgetFactory.getWidget(model, null, null, TagType.TD);
                WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
                WidgetFactory.addWidgetNode(model,widgetNode);
            }
        }
    }

    @Override
    public String getName() {
        return "tdStartLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList(
                SELECTOR_VALUE,
                SELECTOR_TYPE,
                this.getName());
        list.add(Constant.EVENT_VALUE);
        list.add(Constant.DATA_FLAG_VALUE);
        list.add(Constant.CHINESE_FONTS);
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}



