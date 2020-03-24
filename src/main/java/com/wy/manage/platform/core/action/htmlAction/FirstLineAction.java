package com.wy.manage.platform.core.action.htmlAction;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.DocType;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.WidgetModel;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class FirstLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null){
                String s = regularValue.get(this.getName()).toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
                //System.out.println(this.getName()+"的代码是:"+s);
                if(s.contains("transitional")){
                    model.getPage().setDocType(DocType.TRANSITIONAL);
                }else if(s.contains("strict")){
                    model.getPage().setDocType(DocType.STRICT);
                }else if(s.contains("frameset")){
                    model.getPage().setDocType(DocType.FRAMESET);
                }else if(s.contains("mobile")){
                    model.getPage().setDocType(DocType.MOBILE);
                }else {
                    model.getPage().setDocType(DocType.HTML5);
                }
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
