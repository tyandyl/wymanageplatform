package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.bean.Result;
import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.utils.UserAgentTools;
import com.wy.manage.platform.core.widget.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class WindowOpenIngress extends Ingress{
    private String source;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            WidgetModel model=new WidgetModel(){
                @Override
                public BasicModel initLoadHtmlModel() {
                    String targetId = this.getParamResult().getParam().get("targetId");
                    if(targetId!=null){
                        WidgetNode widgetNode = this.getPage().getWidgetNodeTree().getNodeMap().get(targetId.split(",")[0]);
                        BlockType blockType = widgetNode.getData().getBlockType();

                        if(blockType.getCode()==3 || blockType.getCode()==5){
                            return  new HtmlModel<Page>(){
                                @Override
                                public String getRegularAddress() {
                                    return "regular/widget.properties";
                                }
                                @Override
                                public String getContentAddress() {
                                    return "template/window4/window.html";
                                }
                            };
                        }
                    }


                    return  new HtmlModel<Page>(){
                        @Override
                        public String getRegularAddress() {
                            return "regular/widget.properties";
                        }
                        @Override
                        public String getContentAddress() {
                            return "template/window5/window.html";
                        }
                    };
                }

                @Override
                public Page loadPage(WidgetModelParamResult paramResult) {
                    HttpSession session = paramResult.getRequest().getSession(true);
                    return Context.get(session.getId());
                }
            }.init(request);

            model.add();
            String targetId = model.getParamResult().getParam().get("targetId");
            if(targetId!=null) {
                WidgetNode widgetNode = model.getPage().getWidgetNodeTree().getNodeMap().get(targetId.split(",")[0]);
                BlockType blockType = widgetNode.getData().getBlockType();
                if (blockType.getCode() == 3 || blockType.getCode() == 5) {
                    Map<String, String> urlContents = model.getPage().getUrlContents();
                    System.out.println(urlContents);
                }
            }

            Result result = model.getParamResult().getResult();
            String strPage = JSONObject.toJSONString(result);
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");//注意是分号，不能是逗号
            OutputStream out = response.getOutputStream();
            out.write(strPage.getBytes("UTF-8"));
        }catch (Exception e){
            System.out.println("创建弹出窗口报错");
        }
    }
}
