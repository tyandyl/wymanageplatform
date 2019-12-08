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
public class ButtonStartLineAction extends BasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();

            if(regularValue!=null && regularValue.get(Constant.SELECTOR_VALUE)!=null){
                String selectorValue = IgnoreTools.ignore(regularValue.get(Constant.SELECTOR_VALUE).toString());
                if(regularValue!=null && regularValue.get(Constant.SELECTOR_TYPE)!=null){
                    Object widgetFlagValue = regularValue.get(Constant.WIDGET_FLAG_VALUE);
                    Object event_value = regularValue.get(Constant.EVENT_VALUE);

                    Object widget_type_value = regularValue.get(Constant.WIDGET_TYPE_VALUE);

                    String selectorType = IgnoreTools.ignore(regularValue.get(Constant.SELECTOR_TYPE).toString());
                    Widget widget = WidgetFactory.getWidgetEx(model, selectorType, selectorValue, TagType.BUTTON,null,null,widgetFlagValue,null);
                    if(regularValue.get(Constant.URL_VALUE)!=null){
                        widget.setUrl(regularValue.get(Constant.URL_VALUE).toString());
                        widget.setUrlIsDefault(true);
                    }
                    if(regularValue.get(Constant.CHINESE_FONTS)!=null){
                        String s = ChinaFontTools.decodeUnicode(String.valueOf(regularValue.get(Constant.CHINESE_FONTS)))+"";
                        //存放button的text 比如.text( '查询');
                        widget.setProDataTitle(s);
                    }

                    if(widget_type_value!=null){
                        widget.setBlockType(BlockType.getBlockType(Integer.valueOf(widget_type_value.toString())));
                    }

                    if(event_value!=null){
                        model.getPage().getEventMap().put(event_value.toString(),widget.getCode());
                    }
    
                    if(regularValue.get(Constant.HANDLE_TYPE_VALUE)!=null){
                        widget.setHandleType(Integer.valueOf(regularValue.get(Constant.HANDLE_TYPE_VALUE).toString()));
                    }
                    WidgetNode widgetNode = WidgetFactory.getWidgetNode(widget,false);
                    WidgetFactory.addWidgetNode(model,widgetNode);
                }
            }else {
                Widget widget = WidgetFactory.getWidget(model, null, null, TagType.BUTTON);
                if(regularValue.get(Constant.URL_VALUE)!=null){
                    widget.setUrl(regularValue.get(Constant.URL_VALUE).toString());
                    widget.setUrlIsDefault(true);
                }
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
                Constant.SELECTOR_VALUE,
                Constant.SELECTOR_TYPE,
                this.getName(),
                Constant.URL_VALUE,
                Constant.HANDLE_TYPE_VALUE,
                Constant.CHINESE_FONTS,
                Constant.WIDGET_TYPE_VALUE);
        list.add(Constant.WIDGET_FLAG_VALUE);
        list.add(Constant.EVENT_VALUE);
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}


