package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.parser.ModelParam;

import java.util.List;

/**
 * Created by tianye
 */
public class BodyEndLineAction extends HtmlStartTagAction{
    @Override
    public void action(ModelParam modelParam) {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "bodyEndLine";
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

