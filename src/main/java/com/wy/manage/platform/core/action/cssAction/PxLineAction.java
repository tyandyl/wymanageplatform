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
public class PxLineAction extends BasicAction {
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof List){
            List<CssBag> cssBags=(List)t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null) {
                StringBuffer pxName = (StringBuffer)regularValue.get("pxName");
                CssBag cssBag = cssBags.get(cssBags.size() - 1);
                if(pxName!=null){
                    StringBuffer pxValue = (StringBuffer)regularValue.get("pxValue");
                    if(pxValue!=null){
                        List<String> list = cssBag.getMap().get(pxName.toString());
                        if(list==null){
                            List<String> list1=new ArrayList<String>();
                            list1.add(pxValue.toString());
                            cssBag.getMap().put(pxName.toString(),list1);
                        }else {
                            if(!list.contains(pxValue.toString())){
                                list.add(pxValue.toString());
                            }
                        }
                    }


                }
            }

        }
    }

    @Override
    public String getName() {
        return "pxLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("pxName");
        list.add("pxValue");
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
