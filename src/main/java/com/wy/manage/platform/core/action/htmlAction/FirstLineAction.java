package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.DocType;
import com.wy.manage.platform.core.widget.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class FirstLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof Page){
            Page page=(Page)t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null){
                String s = regularValue.get(this.getName()).toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
                //System.out.println(this.getName()+"的代码是:"+s);
                if(s.contains("transitional")){
                    page.setDocType(DocType.TRANSITIONAL);
                }else if(s.contains("strict")){
                    page.setDocType(DocType.STRICT);
                }else if(s.contains("frameset")){
                    page.setDocType(DocType.FRAMESET);
                }else if(s.contains("mobile")){
                    page.setDocType(DocType.MOBILE);
                }else {
                    page.setDocType(DocType.HTML5);
                }
                page.getStr().append(s);
                page.getStr().append("\n");
            }

        }
    }

    @Override
    public String getName() {
        return "firstLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        return null;
    }


    @Override
    public int getPriority() {
        return 2;
    }

}
