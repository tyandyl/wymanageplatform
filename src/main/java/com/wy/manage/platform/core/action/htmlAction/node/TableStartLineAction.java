package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.constant.Constant;
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

            if(regularValue!=null && regularValue.get(Constant.SELECTOR_VALUE)!=null){
                String value = IgnoreTools.ignore(regularValue.get(Constant.SELECTOR_VALUE).toString());

                Object widgetTitleValue = regularValue.get(Constant.WIDGET_TITLE_VALUE);
                Object widgetFlagValue = regularValue.get(Constant.WIDGET_FLAG_VALUE);
                Object widget_type_value = regularValue.get(Constant.WIDGET_TYPE_VALUE);


                if(regularValue!=null && regularValue.get(Constant.SELECTOR_TYPE)!=null){
                    String s = IgnoreTools.ignore(regularValue.get(Constant.SELECTOR_TYPE).toString());
                    Widget widget = WidgetFactory.getWidgetEx(model, s, value, TagType.TABLE,null,null,widgetFlagValue,widgetTitleValue);
                    if(widget_type_value!=null){
                        widget.setBlockType(BlockType.getBlockType(Integer.valueOf(widget_type_value.toString())));
                    }

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
        list.add(Constant.SELECTOR_TYPE);
        list.add(Constant.SELECTOR_VALUE);
        list.add(Constant.CHINESE_FONTS);
        list.add(Constant.DATA_FLAG_VALUE);
        list.add(Constant.WIDGET_TITLE_VALUE);
        list.add(Constant.WIDGET_FLAG_VALUE);
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}



