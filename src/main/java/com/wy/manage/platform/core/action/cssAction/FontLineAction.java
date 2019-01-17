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
public class FontLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof List){
            List<CssBag> cssBags=(List)t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null) {
                String fontValue = IgnoreTools.ignore(regularValue.get("fontValue").toString());
                CssBag cssBag = cssBags.get(cssBags.size() - 1);
                if(fontValue!=null){
                    List<String> list = cssBag.getMap().get("font");
                    if(list==null){
                        List<String> list1=new ArrayList<String>();
                        list1.add(fontValue);
                        cssBag.getMap().put("font",list1);
                    }else {
                        list.add(fontValue);
                    }
                }


            }

        }
    }

    @Override
    public String getName() {
        return "fontLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("fontValue");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
