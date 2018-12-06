package com.wy.manage.platform.core.action.cssAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.SelectorType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class PositionLineAction extends CssBasicAction {
    public void action(ModelParam modelParam) {
        super.action(modelParam);
    }

    public String getName() {
        return "positionLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("positionValue");
        return list;
    }

    @Override
    public String getProName() {
        return "position";
    }

    @Override
    public String getProRegularValue() {
        return "positionValue";
    }

    @Override
    public int getPriority() {
        return 1;
    }


}
