package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.WidgetNode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by tianye
 */
public class WindowIngress extends Ingress{

    public void handleEx( HttpServletRequest request) throws javax.servlet.ServletException, IOException {
        htmlAddress=baseAddress+"window/";
        htmlName="window.html";
        htmlModel=new HtmlModel<Page>();
        Context.clear();
        page=new Page();
        HttpSession session = request.getSession(true);
        Context.put(session.getId(),page);
    }

    @Override
    public void afterHandle(javax.servlet.http.HttpServletResponse response) throws Exception {
        Map<String, Page> map = Context.getMap();
        OutputStream out = response.getOutputStream();
        out.write(page.getStr().toString().getBytes());
    }

}
