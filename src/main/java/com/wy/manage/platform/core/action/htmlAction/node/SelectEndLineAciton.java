package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.WidgetFactory;
import com.wy.manage.platform.core.widget.WidgetModel;
import com.wy.manage.platform.core.widget.WidgetNodeTree;

import java.util.List;

/**
 * Created by tianye13 on 2019/3/29.
 */
public class SelectEndLineAciton extends BasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            WidgetNodeTree widgetNodeTree = model.getPage().getWidgetNodeTree();
            WidgetFactory.handleEvent(model);
            //闭环校验，校验一些div名称之类的，目前先不校验
            widgetNodeTree.getNewestNoClosed().pop();
        }
    }

    @Override
    public String getName() {
        return "selectEndLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        return null;
    }

    @Override
    public int getPriority() {
        return 1;
    }

}



