package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.WidgetNode;
import com.wy.manage.platform.core.widget.WidgetNodeTree;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye13 on 2019/1/14.
 */
public class DivEndLineAction extends BasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof Page) {
            Page page = (Page) t;
            WidgetNodeTree widgetNodeTree = page.getWidgetNodeTree();
            Stack<WidgetNode> newestNoClosed = widgetNodeTree.getNewestNoClosed();
            WidgetNode peek = newestNoClosed.peek();
            peek.setClosed(true);
            page.getStr().append("</div>");
        }
    }

    @Override
    public String getName() {
        return "divEndLine";
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


