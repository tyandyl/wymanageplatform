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
public class BackgroundLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof List){
            List<CssBag> cssBags=(List)t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get("backgroundUnits")!=null) {
                String s = regularValue.get("backgroundUnits").toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
                CssBag cssBag = cssBags.get(cssBags.size() - 1);
                List<String> list = cssBag.getMap().get("background");
                if(s!=null && s.length()>1){
                    String substring = s.substring(0, s.length() - 1);
                    if(list==null){
                        List<String> list1=new ArrayList<String>();
                        list1.add(substring);
                        cssBag.getMap().put("background",list1);
                    }else {
                        list.add(substring);
                    }
                }



            }

        }
    }

    @Override
    public String getName() {
        return "backgroundLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("backgroundUnits");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
