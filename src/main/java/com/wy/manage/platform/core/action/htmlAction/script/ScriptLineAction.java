package com.wy.manage.platform.core.action.htmlAction.script;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.FileTools;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.Script;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class ScriptLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof Page) {
            Page page = (Page) t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null){
                String s = regularValue.get(this.getName()).toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
                //System.out.println(this.getName()+"的代码是:"+s);
                Script script=new Script();
                StringBuffer scriptTypeValue = (StringBuffer)regularValue.get("scriptTypeValue");
                if(scriptTypeValue!=null){
                    script.setType(scriptTypeValue.toString());
                }
                StringBuffer scriptSrcValue = (StringBuffer)regularValue.get("scriptSrcValue");
                if(scriptSrcValue!=null){
                    script.setSrc(scriptSrcValue.toString());
                }
                page.addScript(script);
//                page.getStr().append(s);
//                page.getStr().append("\n");
                StringBuffer str=new StringBuffer();
                str.append("<script type=\"");
                str.append(scriptTypeValue);
                str.append("\">");
                StringBuffer content = FileTools.getContent("jquery/jquery-1.6.2.min.js",false);
                str.append(content);
                str.append("</script>");
                page.getStr().append(str);
            }
        }

    }

    @Override
    public String getName() {
        return "scriptLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("scriptTypeValue");
        list.add("scriptSrcValue");
        list.add(this.getName());
        return list;
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
