package com.wy.manage.platform.core.action.cssAction.bg;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.utils.TempTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class BackgroundLineAction extends CssBasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "backgroundLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList("background-color",
                "background",
                "background-repeat",
                "background-image",
                "background-position");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
