package com.wy.manage.platform.core.action.cssAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.SelectorType;

import java.util.List;

/**
 * Created by tianye
 */
public class AttributeTagAction extends BasicAction {
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof CssBag){
            CssBag cssBag=(CssBag)t;
            StringBuffer curModelValue = modelParam.getCurModelValue();
            char aChar = curModelValue.toString().trim().charAt(0);
            if(aChar==46){
                cssBag.setSelectorType(SelectorType.ID_SELECTOR);
            }else if(aChar==35){
                cssBag.setSelectorType(SelectorType.ID_SELECTOR);
            }
        }
    }

    public String getName() {
        return "attributeTag";
    }


    @Override
    public List<String> getIntraGroupNames() {
        return null;
    }

    @Override
    public int getPriority() {
        return 1;
    }

}
