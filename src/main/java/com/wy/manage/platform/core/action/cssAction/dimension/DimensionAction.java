package com.wy.manage.platform.core.action.cssAction.dimension;

import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.TempTools;

import java.util.List;

/**
 * Created by tianye
 */
public class DimensionAction extends CssBasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "dimensionLine";
    }
    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList("margin",
                "padding",
                "height",
                "width",
                "min-width",
                "max-width",
                "min-height",
                "max-height");
        return list;
    }
}
