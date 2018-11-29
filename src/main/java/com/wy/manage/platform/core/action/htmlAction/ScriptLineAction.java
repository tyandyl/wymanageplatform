package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;

import java.util.Map;

/**
 * Created by tianye
 */
public class ScriptLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Map regularValue = modelParam.getRegularValue();
        if(regularValue!=null && regularValue.get(this.getName())!=null){
            String s = regularValue.get(this.getName()).toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
            System.out.println(this.getName()+"的代码是:"+s);
        }
    }

    @Override
    public String getName() {
        return "scriptLine";
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
