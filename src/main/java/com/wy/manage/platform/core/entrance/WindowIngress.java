package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.widget.*;
import org.apache.commons.lang.StringUtils;

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
public class WindowIngress extends Ingress{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            WidgetModel model=new WidgetModel(){
                @Override
                public void handleType(WidgetModelParamResult paramResult){
                    paramResult.setAddType(AddType.getAddType(1));
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
            }.init(request);
            model.add();
            String view = model.getPage().toView();
            OutputStream out = response.getOutputStream();
            out.write(view.getBytes());
        }catch (Exception e){
            System.out.println("创建窗口报错");
        }



    }


}
