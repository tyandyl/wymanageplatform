package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.widget.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class HtmlStartTagAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof Page) {
            Page page = (Page) t;
            String regularValue1 = getRegularValue(modelParam);
            page.getStr().append(regularValue1);
        }

    }

    @Override
    public String getName() {
        return "htmlStartTag";
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
