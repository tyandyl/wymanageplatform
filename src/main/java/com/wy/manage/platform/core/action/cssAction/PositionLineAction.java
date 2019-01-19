package com.wy.manage.platform.core.action.cssAction;

import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.ModelParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye
 */
public class PositionLineAction extends CssBasicAction {
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    public String getName() {
        return "positionLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("positionValue");
        return list;
    }



    @Override
    public int getPriority() {
        return 1;
    }


}
