package com.wy.manage.platform.core.action;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.IgnoreTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public abstract class CssBasicAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof List){
            List<CssBag> cssBags=(List)t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null) {
                StringBuffer buffer = (StringBuffer)regularValue.get(getName());
                if(buffer==null
                        || buffer.toString().trim().replaceAll("\\n", "").replaceAll("\\r", "").equalsIgnoreCase("")){
                    ExceptionTools.ThrowException("cssValue没值");
                    return;
                }

                String cssStop = IgnoreTools.ignore(modelParam.getRegularValue().get("cssStop").toString().trim());
                if(!(";".equalsIgnoreCase(cssStop.trim()) || "}".equalsIgnoreCase(cssStop.trim()))){
                    ExceptionTools.ThrowException("css结尾有问题"+cssStop);
                }

                String proValue = IgnoreTools.ignore(regularValue.get(getName()).toString());

                int i = proValue.indexOf(cssStop);
                String cssValue=proValue.substring(0,i);
                List<String> intraGroupNames = getIntraGroupNames();
                int w=0;
                String name=null;
                for(String m:intraGroupNames){
                    if(cssValue.contains(m)){
                        w++;
                        if(name==null){
                            name=m;
                        }else {
                            if(name.length()<m.length()){
                                name=m;
                            }
                        }

                    }
                }
                if(w==0){
                    ExceptionTools.ThrowException("css没定义");
                }

                CssBag cssBag = cssBags.get(cssBags.size() - 1);
                List<String> values = cssBag.getValues();
                if(values.contains(cssValue.split(":")[1].trim())){
                    return;
                }
                values.add(cssValue.split(":")[1].trim());
                List<String> list = cssBag.getMap().get(name);
                if(list==null){
                    List<String> list1=new ArrayList<String>();
                    list1.add(cssValue.split(":")[1]);
                    cssBag.getMap().put(name,list1);
                }else {
                    list.add(cssValue.split(":")[1]);
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


    @Override
    public int getPriority() {
        return 1;
    }
}
