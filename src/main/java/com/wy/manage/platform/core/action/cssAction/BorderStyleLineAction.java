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
public class BorderStyleLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof List){
            List<CssBag> cssBags=(List)t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get("borderStyleValue")!=null) {
                String s = regularValue.get("borderStyleValue").toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
                CssBag cssBag = cssBags.get(cssBags.size() - 1);
                List<String> list = cssBag.getMap().get("border-style");
                if(list==null){
                    List<String> list1=new ArrayList<String>();
                    list1.add(s);
                    cssBag.getMap().put("border-style",list1);
                }else {
                    list.add(s);
                }


            }

        }
    }

    @Override
    public String getName() {
        return "borderStyleLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("borderStyleValue");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
