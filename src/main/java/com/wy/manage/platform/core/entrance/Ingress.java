package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.utils.FileTools;
import com.wy.manage.platform.core.widget.*;

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
            String baseAddress=null;
            String htmlAddress=null;
            String htmlName=null;
            HtmlModel<Page> htmlModel=null;
            if(widget.equalsIgnoreCase("window")){
                baseAddress= "template/";
                htmlAddress=baseAddress+"window/";
                htmlName="window.html";
                htmlModel=new HtmlModel<Page>();
            }else if(widget.equalsIgnoreCase("button")){
                baseAddress= "template/";
                htmlAddress=baseAddress+"button/";
                htmlName="button.html";
                htmlModel=new HtmlModel<Page>(){
                    public String getAddress() {
                        return "regular/widget.properties";
                    }
                };
            }else if(widget.equalsIgnoreCase("dog")){
                baseAddress= "template/";
                htmlAddress=baseAddress+"cartoon/";
                htmlName="dog.html";
                htmlModel=new HtmlModel<Page>(){
                    public String getAddress() {
                        return "regular/widget.properties";
                    }
                };
            }
            StringBuffer stringBuffer = FileTools.getContent(htmlAddress + htmlName,true);


            htmlModel.defineAction();
            Page page=new Page();
            page.setParamMap( request.getParameterMap());
            page.setBaseAddress(baseAddress);
            page.setHtmlAddress(htmlAddress);
            page.setHtmlName(htmlName);

            htmlModel.execute(stringBuffer.toString(),page);

            response.setHeader("content-type", "text/html;charset=UTF-8");//注意是分号，不能是逗号
            OutputStream out = response.getOutputStream();
            if(widget.equalsIgnoreCase("window")){
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
