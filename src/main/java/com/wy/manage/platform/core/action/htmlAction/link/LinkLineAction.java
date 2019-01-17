package com.wy.manage.platform.core.action.htmlAction.link;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.model.CssModel;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.FileTools;
import com.wy.manage.platform.core.widget.Link;
import com.wy.manage.platform.core.widget.Page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
                try {
                    System.out.println("css的地址是:"+link.getHref());
                    String[] isAnalyzes = page.getParamMap().get("isAnalyze");

                    if(link.getHref().contains("bootstrap")){
                        StringBuffer str=new StringBuffer();
                        str.append("<link rel=\"stylesheet\" style=\"text/css\" href=\"");
                        str.append(link.getHref());
                        str.append("\" />");
                        page.getStr().append(str);
                    }else if(isAnalyzes==null || !isAnalyzes[0].equalsIgnoreCase("1")){
                        String fileValue = FileTools.getFileValue(link.getHref(), page,true);
                        StringBuffer str=new StringBuffer();
                        str.append("<style>");
                        str.append(fileValue);
                        str.append("</style>");
                        page.getStr().append(str);
                    }else if(isAnalyzes!=null && isAnalyzes[0].equalsIgnoreCase("1")){
                        String fileValue = FileTools.getFileValue(link.getHref(), page,true);
                        CssModel<List<CssBag>> cssModel=new CssModel<List<CssBag>>();
                        cssModel.defineAction();
                        List<CssBag> css=new ArrayList<CssBag>();
                        cssModel.execute(fileValue,css);
                        if(css!=null){
                            Map<String,CssBag> map=new HashMap<String, CssBag>();
                            for(CssBag cssBag:css){
                                if(map.get(cssBag.getName().trim())!=null){
                                    System.out.println("重复啦啦啦啦啦啦");
                                }
                                map.put(cssBag.getName().trim(),cssBag);
                            }
                            page.getCssMaps().putAll(map);
                        }
                    }



                }catch (Exception e){
                    System.out.println(e);
                }

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
