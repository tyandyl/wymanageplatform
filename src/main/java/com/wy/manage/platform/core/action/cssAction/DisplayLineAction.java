package com.wy.manage.platform.core.action.cssAction;

import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.ModelParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye
 */
public class DisplayLineAction extends CssBasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "displayLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("displayValue");
        return list;
    }



    @Override
    public int getPriority() {
        return 1;
    }
}
