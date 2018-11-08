package com.wy.manage.platform.core.action;

import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;

/**
 * Created by tianye
 */
public class AttributeFirstLineAction extends BasicAction{
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof CssBag){
            CssBag cssBag=(CssBag)t;
            String trim = modelParam.getCurModelValue().toString().trim();
            String substring = trim.substring(0, trim.length() - 1);
            cssBag.setName(substring);
        }
    }

    public String getName() {
        return "attributeFirstLine";
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public String getPriority() {
        return null;
    }

}
