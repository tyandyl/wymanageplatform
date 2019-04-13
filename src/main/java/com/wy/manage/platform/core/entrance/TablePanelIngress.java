package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.bean.Result;
import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.CurWidget;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.WidgetModel;
import com.wy.manage.platform.core.widget.WidgetModelParamResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by tianye
 */
public class TablePanelIngress extends Ingress{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            WidgetModel model=new WidgetModel(){
                @Override
                public BasicModel initLoadHtmlModel() {
                    return  new HtmlModel<Page>(){
                        @Override
                        public String getRegularAddress() {
                            return "regular/widget.properties";
                        }
                        @Override
                        public String getContentAddress() {
                            return "template/tablepanel/tablepanel.html";
                        }
                    };
                }
                @Override
                public void handleParamMap(WidgetModelParamResult paramResult){
                    super.handleParamMap(paramResult);
                    String[] fieldTitle = paramResult.getJParam().get("fieldTitle");
                    if(fieldTitle!=null){
                        paramResult.getParam().put("fieldTitle",fieldTitle[0]);
                    }
                    String[] fieldName = paramResult.getJParam().get("fieldName");
                    if(fieldName!=null){
                        paramResult.getParam().put("fieldName",fieldName[0]);
                    }
                    String[] fieldType = paramResult.getJParam().get("fieldType");
                    if(fieldType!=null){
                        paramResult.getParam().put("fieldType",fieldType[0]);
                    }
                }

                @Override
                public Page loadPage(WidgetModelParamResult paramResult) {
                    HttpSession session = paramResult.getRequest().getSession(true);
                    return Context.get(session.getId());
                }
            }.init(request).execute();
            Result result = model.getParamResult().getResult();
            String strPage = JSONObject.toJSONString(result);
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");//注意是分号，不能是逗号
            OutputStream out = response.getOutputStream();
            out.write(strPage.getBytes("UTF-8"));
        }catch (Exception e){
            System.out.println("创建输入面板报错");
        }
    }
}
