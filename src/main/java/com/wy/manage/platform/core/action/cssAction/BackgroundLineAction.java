package com.wy.manage.platform.core.action.cssAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;

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
            if(regularValue!=null && regularValue.get(getName())!=null) {
                String s = regularValue.get(getName()).toString();
                CssBag cssBag = cssBags.get(cssBags.size() - 1);
                List<String> list = cssBag.getMap().get("background");
                if(s!=null && s.length()>1){
                    String substring = s.substring(0, s.length() - 1);
                    String[] split = substring.split(":");
                    if(list==null){
                        List<String> list1=new ArrayList<String>();
                        list1.add(split[1]);
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
