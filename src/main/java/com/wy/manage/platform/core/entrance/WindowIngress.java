package com.wy.manage.platform.core.entrance;

import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by tianye
 */
public class WindowIngress extends Ingress{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            WidgetModel model=new WidgetModel(){
                @Override
                public void handleType(WidgetModelParamResult paramResult){
                    paramResult.setHandleType(HandleType.getHandleType(1));
                }

                @Override
                public BasicModel initLoadHtmlModel() {
                    return new HtmlModel(){
                        @Override
                        public String getContentAddress() {
                            return "template/window/window.html";
                        }
                    };
                }

                @Override
                public Page loadPage(WidgetModelParamResult widgetModelParamResult) {
                    return null;
                }
            }.init(request).execute();
            String view = model.getPage().toView();
            OutputStream out = response.getOutputStream();
            response.setHeader("content-type", "text/html;charset=UTF-8");//注意是分号，不能是逗号
            response.setCharacterEncoding("utf-8");
            out.write(view.getBytes());
        }catch (Exception e){
            System.out.println("创建窗口报错");
        }



    }


}
