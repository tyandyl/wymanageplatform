package com.wy.manage.platform.core.action.cssAction.font;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.action.CssBasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.utils.TempTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class FontGroupLineAction extends CssBasicAction {
    @Override
    public void action(ModelParam modelParam)throws Exception {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "fontGroupLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> cssStop = TempTools.createList("font",
                "font-family",
                "-webkit-text-size-adjust",
                "-ms-text-size-adjust");
        return cssStop;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
