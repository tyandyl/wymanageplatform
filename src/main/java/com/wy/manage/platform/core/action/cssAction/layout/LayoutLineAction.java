package com.wy.manage.platform.core.action.cssAction.layout;

import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.TempTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye
 */
public class LayoutLineAction extends CssBasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "layoutLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList(
                "display",
                "overflow",
                "float");
        return list;
    }



    @Override
    public int getPriority() {
        return 1;
    }
}
