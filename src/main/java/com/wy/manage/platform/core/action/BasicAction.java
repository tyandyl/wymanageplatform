package com.wy.manage.platform.core.action;

import com.wy.manage.platform.core.parser.ModelParam;

/**
 * Created by tianye
 */
public abstract class BasicAction implements Action{
    public String value;
    public boolean isExecuted;
    public abstract void action(ModelParam modelParam);

    public abstract String getName();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value=value;
    }

    public abstract String getGroup();

    public abstract int getPriority();

    public boolean isExecuted(){
        return isExecuted;
    }
    public void setExecuted(boolean isExecuted){
        this.isExecuted=isExecuted;
    }
}
