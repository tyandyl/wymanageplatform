package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.ChinaFontTools;
import com.wy.manage.platform.core.utils.TempTools;
import com.wy.manage.platform.core.widget.CurWidget;
import com.wy.manage.platform.core.widget.WidgetModel;
import com.wy.manage.platform.core.widget.WidgetNode;
import com.wy.manage.platform.core.widget.WidgetNodeTree;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye13 on 2019/3/4.
 */
public class LegendEndLineAction extends BasicAction {
    private static final String CHINESE_FONTS="ChineseFonts";

    @Override
    public void action(ModelParam modelParam) throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            WidgetNodeTree widgetNodeTree = model.getPage().getWidgetNodeTree();
            //闭环校验，校验一些div名称之类的，目前先不校验
            widgetNodeTree.getNewestNoClosed().pop();
            Map regularValue = modelParam.getRegularValue();
            Object chineseFonts = regularValue.get(CHINESE_FONTS);
            if(chineseFonts!=null){
                List<CurWidget> curWidgets = model.getParamResult().getCurWidgets();
                CurWidget curWidget = curWidgets.get(curWidgets.size() - 1);
                String s = ChinaFontTools.decodeUnicode(String.valueOf(chineseFonts))+"";
                //很奇怪，一个汉字：宋就乱码，两个汉字就不乱码
                curWidget.setOutContentValue(s);
            }


        }
    }

    @Override
    public String getName() {
        return "legendEndLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList(CHINESE_FONTS);
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
