package com.wy.manage.platform.core.action;

import com.wy.manage.platform.core.parser.ModelParam;

import java.util.List;

/**
 * Created by tianye
 */
public abstract class CommonAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
    }

    @Override
    public abstract String getName();

    @Override
    public List<String> getGroupNames() {
        return null;
    }

    @Override
    public int getPriority() {
        return 999;
    }
}
