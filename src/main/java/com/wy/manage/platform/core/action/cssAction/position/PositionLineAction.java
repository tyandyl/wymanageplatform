package com.wy.manage.platform.core.action.cssAction.position;

import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.TempTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye
 */
public class PositionLineAction extends CssBasicAction {
    public void action(ModelParam modelParam)throws Exception {
        //当前动作,如果后边跟着ignore又遇到该动作，不执行
        super.action(modelParam);
    }

    public String getName() {
        return "positionLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList("position",
                "top",
                "left",
                "right",
                "bottom",
                "z-index");
        return list;
    }



    @Override
    public int getPriority() {
        return 1;
    }


}
