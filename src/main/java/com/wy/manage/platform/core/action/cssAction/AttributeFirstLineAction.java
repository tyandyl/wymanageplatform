package com.wy.manage.platform.core.action.cssAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.SelectorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class AttributeFirstLineAction extends BasicAction {
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof List){
            List<CssBag> cssBags=(List)t;
            CssBag cssBag=new CssBag();
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null) {
                String s = regularValue.get(this.getName()).toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
                StringBuffer attributeTag = (StringBuffer)regularValue.get("attributeTag");
                String s1 = attributeTag.toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
                if(s1!=null){
                    char aChar = s1.charAt(0);
                      if(aChar==46){
                        cssBag.setSelectorType(SelectorType.ID_SELECTOR);
                    }else if(aChar==35){
                        cssBag.setSelectorType(SelectorType.ID_SELECTOR);
                    }
                }
                cssBag.setName(s.substring(1,s.length()-1));
                cssBags.add(cssBag);
            }

        }
    }

    public String getName() {
        return "attributeFirstLine";
    }


    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("attributeTag");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }

}
