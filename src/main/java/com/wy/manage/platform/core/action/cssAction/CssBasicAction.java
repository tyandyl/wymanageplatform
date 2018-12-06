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
public abstract class CssBasicAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof List){
            List<CssBag> cssBags=(List)t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null) {
                StringBuffer buffer = (StringBuffer)regularValue.get(getProRegularValue());
                if(buffer==null
                        || buffer.toString().trim().replaceAll("\\n", "").replaceAll("\\r", "").equalsIgnoreCase("")){
                    return;
                }
                String proValue = regularValue.get(getProRegularValue()).toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
                CssBag cssBag = cssBags.get(cssBags.size() - 1);
                List<String> list = cssBag.getMap().get(getProName());
                if(list==null){
                    List<String> list1=new ArrayList<String>();
                    list1.add(proValue);
                    cssBag.getMap().put(getProName(),list1);
                }else {
                    list.add(proValue);
                }


            }

        }
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public abstract List<String> getIntraGroupNames();

    public abstract String getProName();

    public abstract String getProRegularValue();

    @Override
    public int getPriority() {
        return 1;
    }
}
