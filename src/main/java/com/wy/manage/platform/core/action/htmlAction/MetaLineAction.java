package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.Page;

/**
 * Created by tianye
 */
public class MetaLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof Page){
            Page page=(Page)t;
            String s = modelParam.getCurModelValue().toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
        }
    }
    @Override
    public String getName() {
        return "metaLine";
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
