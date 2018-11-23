package com.wy.manage.platform.core.action.cssAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;

/**
 * Created by tianye
 */
public class CssAttributeFullAction extends BasicAction {
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof CssBag){
            CssBag cssBag=(CssBag)t;
            String trim = modelParam.getCurModelValue().toString().trim();
            String substring = trim.substring(0, trim.length() - 1).replaceAll("\\n","").replaceAll("\\r","");
            String[] split1 = substring.split(";");
            if(split1!=null && split1.length>0){
                for(int i=0;i<split1.length;i++){
                    String[] split = split1[i].split(":");
                    cssBag.getMap().put(split[0],split[1]);
                }
            }

        }
    }

    public String getName() {
        return "cssAttributeFull";
    }

    @Override
    public String getGroup() {
        return null;
    }

    @Override
    public int getPriority() {
        return 0;
    }


}
