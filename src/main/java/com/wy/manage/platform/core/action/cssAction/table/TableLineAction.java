package com.wy.manage.platform.core.action.cssAction.table;

import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.TempTools;

import java.util.List;

public class TableLineAction extends CssBasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "tableLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList("border-collapse",
                "border-spacing");
        return list;
    }



    @Override
    public int getPriority() {
        return 1;
    }
}


