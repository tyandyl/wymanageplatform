package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.WidgetNode;
import com.wy.manage.platform.core.widget.WidgetNodeTree;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class TdEndLineAction extends BasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof Page) {
            Page page = (Page) t;
            WidgetNodeTree widgetNodeTree = page.getWidgetNodeTree();
            //闭环校验，校验一些div名称之类的，目前先不校验
            widgetNodeTree.getNewestNoClosed().pop();
            //3的时候没人用，这里使用一下
            if(page.getFirstIsCame()==3){
                String gbk = new String("订单编号".getBytes("utf-8"));
                page.getStr().append(gbk+"</td>");
                page.setFirstIsCame(4);
            }else {
                page.getStr().append("</td>");
            }

        }
    }

    @Override
    public String getName() {
        return "tdEndLine";
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



