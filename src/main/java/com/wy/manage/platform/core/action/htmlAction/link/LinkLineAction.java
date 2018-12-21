package com.wy.manage.platform.core.action.htmlAction.link;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.model.CssModel;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
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
                try {
                    System.out.println("css的地址是:"+link.getHref());
                    URL resource = LinkLineAction.class.getClassLoader().getResource("template/simple/window/win.css");
                    File file=new File(resource.toURI());

                    Reader fr = new FileReader(file);
                    BufferedReader bufr = new BufferedReader(fr);
                    StringBuffer stringBuffer=new StringBuffer();
                    String line = null;
                    while((line = bufr.readLine())!=null) {
                        if(!(line.contains("/**") || line.contains("**/"))){
                            stringBuffer.append(line);
                        }

                    }
                    System.out.println("打印读取css配置文件的日志:"+stringBuffer);
                    CssModel<List<CssBag>> cssModel=new CssModel<List<CssBag>>();
                    cssModel.defineAction();
                    List<CssBag> css=new ArrayList<CssBag>();
                    cssModel.execute(stringBuffer.toString(),css);
                    if(css!=null){
                        Map<String,CssBag> map=new HashMap<String, CssBag>();
                        for(CssBag cssBag:css){
                            if(map.get(cssBag.getName())!=null){
                                System.out.println("重复啦啦啦啦啦啦");
                            }
                            map.put(cssBag.getName(),cssBag);
                        }
                        modelParam.setMap(map);
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
