package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.WidgetNode;
import com.wy.manage.platform.core.widget.WidgetNodeTree;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by tianye
 */
public class ButtonIngress extends Ingress{

    public void handleEx(HttpServletRequest request) throws javax.servlet.ServletException, IOException {
        page.setFirstIsCame(1);
        htmlAddress=baseAddress+"button/";
        htmlName="button.html";
        htmlModel=new HtmlModel<Page>(){
            public String getAddress() {
                return "regular/widget.properties";
            }
        };

    }

    @Override
    public void afterHandle( javax.servlet.http.HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        String strPage = JSONObject.toJSONString(page);
        out.write(strPage.getBytes());
    }

    public void proPage(){
        String curWd = page.getCurWd();
        WidgetNodeTree widgetNodeTree = page.getWidgetNodeTree();
        Map<String, WidgetNode> nodeMap = widgetNodeTree.getNodeMap();
        WidgetNode widgetNode = nodeMap.get(curWd);

    }


}