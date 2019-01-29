package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.utils.FileTools;
import com.wy.manage.platform.core.utils.GUIDTools;
import com.wy.manage.platform.core.widget.*;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by tianye
 */
public class Ingress {

    public String widget;

    public Ingress(String widget){
        this.widget=widget;
    }

    public void handle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(30*60);
            Page page=new Page();

            Page page1 = Context.get(session.getId());
            if(page1!=null){
                page=page1;
            }
            String baseAddress="template/";
            String htmlAddress=null;
            String htmlName=null;
            HtmlModel<Page> htmlModel=null;
            String flag=null;
            if(widget.equalsIgnoreCase("window")){
                String[] wins = request.getParameterMap().get("win");
                if(wins!=null && wins.length>0){
                    if(wins[0].equalsIgnoreCase("2")){
                        flag="1";
                        page.setFirstIsCame(1);
                        htmlAddress=baseAddress+"window2/";
                        htmlName="window.html";
                        htmlModel=new HtmlModel<Page>(){
                            public String getAddress() {
                                return "regular/widget.properties";
                            }
                        };
                    }
                }else {
                    htmlAddress=baseAddress+"window/";
                    htmlName="window.html";
                    htmlModel=new HtmlModel<Page>();
                    Context.clear();
                    page=new Page();
                    Context.put(session.getId(),page);
                }

            }else if(widget.equalsIgnoreCase("button")){
                page.setFirstIsCame(1);
                htmlAddress=baseAddress+"button/";
                htmlName="button.html";
                htmlModel=new HtmlModel<Page>(){
                    public String getAddress() {
                        return "regular/widget.properties";
                    }
                };
            }else if(widget.equalsIgnoreCase("dog")){
                page.setFirstIsCame(1);
                htmlAddress=baseAddress+"cartoon/";
                htmlName="dog.html";
                htmlModel=new HtmlModel<Page>(){
                    public String getAddress() {
                        return "regular/widget.properties";
                    }
                };
            }else if(widget.equalsIgnoreCase("TablePanel")){
                page.setFirstIsCame(1);
                htmlAddress=baseAddress+"tablepanel/";
                htmlName="tablepanel.html";
                htmlModel=new HtmlModel<Page>(){
                    public String getAddress() {
                        return "regular/widget.properties";
                    }
                };
            }

            page.setStr(new StringBuffer());

            StringBuffer stringBuffer = FileTools.getContent(htmlAddress + htmlName,true);


            htmlModel.defineAction();
            page.setParamMap( request.getParameterMap());
            page.setBaseAddress(baseAddress);
            page.setHtmlAddress(htmlAddress);
            page.setHtmlName(htmlName);

            htmlModel.execute(stringBuffer.toString(),page);

            response.setHeader("content-type", "text/html;charset=UTF-8");//注意是分号，不能是逗号
            OutputStream out = response.getOutputStream();
            if(widget.equalsIgnoreCase("window") && StringUtils.isBlank(flag)){
                out.write(page.getStr().toString().getBytes());
            }else {
                String strPage = JSONObject.toJSONString(page);
                out.write(strPage.getBytes());
            }

        }catch (Exception e){
            System.out.println(e);
        }

    }


    public String getWidget() {
        return widget;
    }

    public void setWidget(String widget) {
        this.widget = widget;
    }
}
