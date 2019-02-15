package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.Page;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;

/**
 * Created by tianye
 */
public class ComboListIngress  extends Ingress{

    public void handleEx(HttpServletRequest request) throws Exception {
        page.setFirstIsCame(1);
        htmlAddress=baseAddress+"combolist/";
        htmlName="combolist.html";
        htmlModel=new HtmlModel<Page>(){
            public String getAddress() {
                return "regular/widget.properties";
            }
        };
    }

    @Override
    public void afterHandle(javax.servlet.http.HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        String strPage = JSONObject.toJSONString(page);
        out.write(strPage.getBytes());
    }
}

