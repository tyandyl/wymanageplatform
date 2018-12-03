package com.wy.manage.platform.core.action;

import com.wy.manage.platform.core.parser.ModelParam;

import java.util.List;

/**
 * Created by tianye
 */
public abstract class BasicAction implements Action{
    public String value;
    public abstract void action(ModelParam modelParam);

    public abstract String getName();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value=value;
    }

    public abstract List<String> getGroupNames();

    public abstract int getPriority();
}
