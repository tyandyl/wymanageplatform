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
public class ColorLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof List){
            List<CssBag> cssBags=(List)t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null) {
                String s = regularValue.get(this.getName()).toString().trim().replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\t", "");
                CssBag cssBag = cssBags.get(cssBags.size() - 1);
                if(s.length()>2){
                    String colorTag = regularValue.get("colorTag").toString().trim().replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\t", "");
                    if(colorTag.length()>2){
                        String hexColor = regularValue.get("hexColor").toString().trim().replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\t", "");
                        List<String> list = cssBag.getMap().get(colorTag);
                        if(list==null){
                            List<String> list1=new ArrayList<String>();
                            list1.add(hexColor);
                            cssBag.getMap().put(colorTag,list1);
                        }else {
                            list.add(hexColor);
                        }
                    }

                }


            }

        }
    }

    @Override
    public String getName() {
        return "colorLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("colorTag");
        list.add("hexColor");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
