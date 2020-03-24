package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class HeadEndTagAction extends HtmlStartTagAction{
    @Override
    public void action(ModelParam modelParam) {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "headEndTag";
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
