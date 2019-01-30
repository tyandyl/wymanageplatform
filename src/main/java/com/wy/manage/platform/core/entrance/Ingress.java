package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.utils.FileTools;
import com.wy.manage.platform.core.utils.GUIDTools;
import com.wy.manage.platform.core.widget.*;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by tianye
 */
public abstract class Ingress {

    public String baseAddress="template/";
    public String htmlAddress=null;
    public String htmlName=null;
    public HtmlModel<Page> htmlModel=null;
    public Page page=null;

    public void beforeHandle(javax.servlet.http.HttpServletRequest request) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(30*60);
        page=new Page();

        Page page1 = Context.get(session.getId());
        if(page1!=null){
            page=page1;
        }
    }

    public void handle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            beforeHandle(request);

            handleEx( page,  request);

            page.setStr(new StringBuffer());

            StringBuffer stringBuffer = FileTools.getContent(htmlAddress + htmlName,true);


            htmlModel.defineAction();
            page.setParamMap( request.getParameterMap());
            page.setBaseAddress(baseAddress);
            page.setHtmlAddress(htmlAddress);
            page.setHtmlName(htmlName);

            htmlModel.execute(stringBuffer.toString(),page);

            response.setHeader("content-type", "text/html;charset=UTF-8");//注意是分号，不能是逗号

            afterHandle(page, response);


        }catch (Exception e){
            System.out.println(e);
        }

    }
    public abstract void afterHandle( Page page,javax.servlet.http.HttpServletResponse response)throws Exception;

    public abstract void handleEx(Page page, HttpServletRequest request) throws Exception;
}
