package com.wy.manage.platform.core.action.cssAction.border;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.TempTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class BorderLineAction extends CssBasicAction {
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    public String getName() {
        return "borderLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList("border",
                "border-bottom",
                "border-radius",
                "border-width",
                "border-color");
        return list;
    }



    @Override
    public int getPriority() {
        return 1;
    }


}
