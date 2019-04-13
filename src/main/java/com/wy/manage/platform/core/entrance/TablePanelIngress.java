package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
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
                public Page loadPage(WidgetModelParamResult paramResult) {
                    HttpSession session = paramResult.getRequest().getSession(true);
                    return Context.get(session.getId());
                }
            }.init(request);
            model.add();
            List<CurWidget> curWidgets = model.getParamResult().getCurWidgets();
            String strPage = JSONObject.toJSONString(curWidgets);
            OutputStream out = response.getOutputStream();
            out.write(strPage.getBytes());
        }catch (Exception e){
            System.out.println("创建输入面板报错");
        }
    }
}
