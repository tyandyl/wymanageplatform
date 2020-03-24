package com.wy.manage.platform.core.action.cssAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;

import java.util.List;

/**
 * Created by tianye
 */
public class CssStopAction extends BasicAction {

    @Override
    public void action(ModelParam modelParam) throws Exception {
    }

    @Override
    public String getName() {
        return "cssStop";
    }

    @Override
    public List<String> getIntraGroupNames() {
        return null;
    }

    @Override
    public int getPriority() {
        return 3;
    }
}
