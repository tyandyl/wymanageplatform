package com.wy.manage.platform.core.action.htmlAction.link;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.widget.Link;
import com.wy.manage.platform.core.widget.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class LinkLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof Page) {
            Page page = (Page) t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null){
                String s = regularValue.get(this.getName()).toString().trim().replaceAll("\\n", "").replaceAll("\\r", "");
                Link link=new Link();
                StringBuffer linkRelValue = (StringBuffer)regularValue.get("linkRelValue");
                if(linkRelValue!=null){
                    link.setRel(linkRelValue.toString());
                }
                StringBuffer linkStyleValue = (StringBuffer) regularValue.get("linkStyleValue");
                if(linkStyleValue!=null){
                    link.setStyle(linkStyleValue.toString());
                }
                StringBuffer linkHref = (StringBuffer) regularValue.get("linkHref");
                if(linkHref!=null){
                    link.setHref(linkHref.toString());
                }
                page.addLink(link);
                page.getStr().append(s);
                page.getStr().append("\n");
               // System.out.println(this.getName()+"的代码是:"+s);
            }
        }

    }

    @Override
    public String getName() {
        return "linkLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("linkRelValue");
        list.add("linkStyleValue");
        list.add("linkHref");
        list.add(this.getName());
        return list;
    }

    @Override
    public int getPriority() {
        return 2;
    }

}
