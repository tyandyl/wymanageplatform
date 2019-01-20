package com.wy.manage.platform.core.action.cssAction.userinterface;

import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.TempTools;

import java.util.List;

/**
 * Created by tianye
 */
public class UserInterfaceLineAction extends CssBasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "userInterfaceLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList("-moz-box-sizing",
                "-webkit-box-sizing",
                "-o-box-sizing",
                "-ms-box-sizing",
                "outline",
                "box-sizing");
        return list;
    }



    @Override
    public int getPriority() {
        return 1;
    }
}


