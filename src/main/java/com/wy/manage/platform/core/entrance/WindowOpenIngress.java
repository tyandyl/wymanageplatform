package com.wy.manage.platform.core.entrance;

import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by tianye
 */
public class WindowOpenIngress extends Ingress{

    public void handleEx(Page page, HttpServletRequest request) throws javax.servlet.ServletException, IOException {
        page.setFirstIsCame(1);
        htmlAddress=baseAddress+"window2/";
        htmlName="window.html";
        htmlModel=new HtmlModel<Page>(){
            public String getAddress() {
                return "regular/widget.properties";
            }
        };
    }

    @Override
    public void afterHandle(Page page, HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        out.write(page.getStr().toString().getBytes());
    }

}
