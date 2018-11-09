package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.Page;

/**
 * Created by tianye
 */
public class HtmlStartTagAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof Page){
            Page page=(Page)t;
           // System.out.println(modelParam.getCurModelValue().toString().replaceAll("\\n", "").replaceAll("\\r", ""));
        }
    }

    @Override
    public String getName() {
        return "htmlStartTag";
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public String getPriority() {
        return null;
    }

}
