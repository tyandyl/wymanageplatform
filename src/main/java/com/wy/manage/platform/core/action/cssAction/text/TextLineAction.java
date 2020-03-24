package com.wy.manage.platform.core.action.cssAction.text;

import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.TempTools;

import java.util.List;

/**
 * Created by tianye
 */
public class TextLineAction extends CssBasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "textLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList("vertical-align",
                "line-height",
                "text-align",
                "text-indent");
        return list;
    }



    @Override
    public int getPriority() {
        return 1;
    }
}

