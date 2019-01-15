package com.wy.manage.platform.core.action;

import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public abstract class BasicAction implements Action{
    public String value;
    public abstract void action(ModelParam modelParam)throws Exception;

    public abstract String getName();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value=value;
    }

    public abstract List<String> getIntraGroupNames();

    public abstract int getPriority();

    public String getRegularValue(ModelParam modelParam){
        Map regularValue = modelParam.getRegularValue();
        String value=null;
        if(regularValue!=null && regularValue.get(this.getName())!=null){
            value = IgnoreTools.ignore(regularValue.get(this.getName()).toString());
        }
        return value;
    }
}
