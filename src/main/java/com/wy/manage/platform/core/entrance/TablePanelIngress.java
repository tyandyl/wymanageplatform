package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by tianye
 */
public class TablePanelIngress extends Ingress{

    public void handleEx(Page page, HttpServletRequest request) throws Exception {
        page.setFirstIsCame(1);
        htmlAddress=baseAddress+"tablepanel/";
        htmlName="tablepanel.html";
        htmlModel=new HtmlModel<Page>(){
            public String getAddress() {
                return "regular/widget.properties";
            }
        };
    }

    @Override
    public void afterHandle(Page page, javax.servlet.http.HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        String strPage = JSONObject.toJSONString(page);
        out.write(strPage.getBytes());
    }
}
