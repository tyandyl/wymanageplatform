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
public class BorderLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof List){
            List<CssBag> cssBags=(List)t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null) {
                String s = regularValue.get(this.getName()).toString();
                CssBag cssBag = cssBags.get(cssBags.size() - 1);
                List<String> list = cssBag.getMap().get("border");
                if(s!=null && s.length()>1){
                    String substring = s.substring(0, s.length() - 2);
                    String[] split = substring.split(":");
                    if(list==null){
                        List<String> list1=new ArrayList<String>();
                        list1.add(split[1]);
                        cssBag.getMap().put("border",list1);
                    }else {
                        list.add(split[1]);
                    }
                }

            }

        }
    }

    @Override
    public String getName() {
        return "borderLine";
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
