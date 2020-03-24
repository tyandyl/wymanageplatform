package com.wy.manage.platform.core.action.cssAction.table;

import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.TempTools;

import java.util.List;

/**
 * Created by tianye
 */
public class TableLineAction extends CssBasicAction {
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    public String getName() {
        return "tableLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList("border-spacing",
                "border-collapse",
                "table-layout",
                "padding-right",
                "padding-top",
                "margin",
                "margin-right",
                "margin-left");
        return list;
    }



    @Override
    public int getPriority() {
        return 1;
    }


}

