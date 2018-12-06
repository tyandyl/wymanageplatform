package com.wy.manage.platform.core.action.cssAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class OverflowLineAction extends CssBasicAction{
    @Override
    public void action(ModelParam modelParam) {
        super.action(modelParam);
    }

    @Override
    public String getName() {
        return "overflowLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("overflowValue");
        return list;
    }

    @Override
    public String getProName() {
        return "overflow";
    }

    @Override
    public String getProRegularValue() {
        return "overflowValue";
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
