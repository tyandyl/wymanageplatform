package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.Page;

import java.util.Map;

/**
 * Created by tianye
 */
public class LinkLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof Page) {
            Page page = (Page) t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null){
                String s = regularValue.get(this.getName()).toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
                //System.out.println(this.getName()+"的代码是:"+s);
                page.getStr().append(s);
            }
        }

    }

    @Override
    public String getName() {
        return "linkLine";
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
