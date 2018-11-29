package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.Page;

import java.util.Map;

/**
 * Created by tianye
 */
public class LinkLinesAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
    }

    @Override
    public String getName() {
        return "linkLines";
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
