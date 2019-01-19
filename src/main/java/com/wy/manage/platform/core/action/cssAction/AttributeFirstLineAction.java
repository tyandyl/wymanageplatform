package com.wy.manage.platform.core.action.cssAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.widget.SelectorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class AttributeFirstLineAction extends BasicAction {
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof List){
            List<CssBag> cssBags=(List)t;
            CssBag cssBag=new CssBag();
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null) {
                String firstLine = IgnoreTools.ignore(regularValue.get(this.getName()).toString());
                if(!firstLine.contains("{")){
                    ExceptionTools.ThrowException("css开头不包含}");
                }
                int i = firstLine.indexOf("{");
                String cssName=firstLine.substring(0,i);
                if(cssName!=null){
                    if(cssName.contains(".")){
                        cssBag.setSelectorType(SelectorType.CLASS_SELECTOR);
                        int i1 = cssName.indexOf(".");
                        cssName=cssName.substring(i1,cssName.length()-1);
                    }else if(cssName.contains("#")){
                        cssBag.setSelectorType(SelectorType.ID_SELECTOR);
                        int i1 = cssName.indexOf("#");
                        cssName=cssName.substring(i1,cssName.length()-1);
                    }else {

                    }
                }
                cssBag.setName(cssName);
                cssBags.add(cssBag);
            }

        }
    }

    public String getName() {
        return "attributeFirstLine";
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
