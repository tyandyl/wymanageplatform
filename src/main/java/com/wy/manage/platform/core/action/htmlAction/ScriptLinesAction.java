package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class ScriptLinesAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
    }

    @Override
    public String getName() {
        return "scriptLines";
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
