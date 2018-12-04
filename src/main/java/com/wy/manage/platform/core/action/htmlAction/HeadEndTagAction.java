package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class HeadEndTagAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Map regularValue = modelParam.getRegularValue();
        if(regularValue!=null && regularValue.get(this.getName())!=null){
            String s = regularValue.get(this.getName()).toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
            //System.out.println(this.getName()+"的代码是:"+s);
        }
    }

    @Override
    public String getName() {
        return "headEndTag";
    }

    @Override
    public List<String> getIntraGroupNames() {
        return null;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
