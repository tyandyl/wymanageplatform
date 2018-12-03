package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;

import java.util.List;

/**
 * Created by tianye
 */
public class MetaLinesAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {

    }

    @Override
    public String getName() {
        return "metaLines";
    }

    @Override
    public List<String> getGroupNames() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
