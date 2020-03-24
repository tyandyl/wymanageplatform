package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.parser.test.Encoding;
import com.wy.manage.platform.core.utils.ChinaFontTools;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.TempTools;
import com.wy.manage.platform.core.widget.CurWidget;
import com.wy.manage.platform.core.widget.WidgetModel;
import com.wy.manage.platform.core.widget.WidgetNode;
import com.wy.manage.platform.core.widget.WidgetNodeTree;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class SpanEndLineAction extends BasicAction {
    private static final String CHINESE_FONTS="ChineseFonts";
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            WidgetNodeTree widgetNodeTree = model.getPage().getWidgetNodeTree();

            Map regularValue = modelParam.getRegularValue();
            //不确定的正则表达式放开头不放结尾
            Object chineseFonts = regularValue.get(CHINESE_FONTS);
            String s =null;
            if(chineseFonts!=null){
                List<CurWidget> curWidgets=null;
                Object result = model.getParamResult().getResult().getResult();
                if(result instanceof List){
                    curWidgets = ((List<CurWidget>)result);
                }else {
                    ExceptionTools.ThrowException("报错,span结束不能为空");
                }
                CurWidget curWidget = curWidgets.get(curWidgets.size() - 1);
                s = ChinaFontTools.decodeUnicode(String.valueOf(chineseFonts));
                curWidget.setOutContentValue(s);

            }
            //闭环校验，校验一些div名称之类的，目前先不校验
            WidgetNode pop = widgetNodeTree.getNewestNoClosed().pop();
            pop.getData().setOutValue(s);
        }
    }

    @Override
    public String getName() {
        return "spanEndLine";
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



