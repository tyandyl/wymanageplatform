package com.wy.manage.platform.core.action.htmlAction.meta;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.widget.Meta;
import com.wy.manage.platform.core.widget.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class MetaLineAction extends BasicAction{
    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof Page) {
            Page page = (Page) t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null){
                String s = IgnoreTools.ignore(regularValue.get(this.getName()).toString());
               // System.out.println(this.getName()+"的代码是:"+s);
                Meta meta=new Meta();
                StringBuffer httpEquivValue = (StringBuffer)regularValue.get("httpEquivValue");
                if(httpEquivValue!=null){
                    meta.setHttp_equiv(httpEquivValue.toString());
                }
                StringBuffer metaContentValue = (StringBuffer)regularValue.get("metaContentValue");
                if(metaContentValue!=null){
                    meta.setContent(metaContentValue.toString());
                }
                page.addMeta(meta);
                StringBuffer str=new StringBuffer();
                str.append("<meta ");
                str.append("http-equiv=\"");
                str.append(meta.getHttp_equiv()+"\" ");
                str.append("content=\"");
                str.append(meta.getContent()+"\">");
                page.getStr().append(str);

            }
        }

    }
    @Override
    public String getName() {
        return "metaLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list=new ArrayList<String>();
        list.add("httpEquivValue");
        list.add("metaContentValue");
        list.add(this.getName());
        return list;
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
