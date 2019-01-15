package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.utils.FileTools;
import com.wy.manage.platform.core.widget.*;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * Created by tianye
 */
public class Ingress {
    public void handle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            String baseAddress="template/simple/";
            String htmlAddress=baseAddress+"window/";
            String htmlName="window.html";
            StringBuffer stringBuffer = FileTools.getContent(htmlAddress + htmlName,true);
            //System.out.println("打印读取窗口日志:"+stringBuffer);

            System.out.println();
            HtmlModel<Page> htmlModel=new HtmlModel<Page>();
            htmlModel.defineAction();
            Page page=new Page();
            page.setBaseAddress(baseAddress);
            page.setHtmlAddress(htmlAddress);
            page.setHtmlName(htmlName);

            htmlModel.execute(stringBuffer.toString(),page);

            //String strPage = JSONObject.toJSONString(page.getStr());
            response.setHeader("content-type", "text/html;charset=UTF-8");//注意是分号，不能是逗号
            OutputStream out = response.getOutputStream();
            out.write(page.getStr().toString().getBytes());
        }catch (Exception e){
            System.out.println(e);
        }


    }




}
