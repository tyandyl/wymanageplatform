package com.wy.manage.platform.core.action;

import com.wy.manage.platform.core.parser.ModelParam;

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

    public abstract String getGroup();

    public abstract String getPriority();
}
