package com.wy.manage.platform.core.entrance;

import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.WidgetNode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tianye
 */
public class DblClickIngress extends Ingress{

    public void handleEx(Page page, HttpServletRequest request) throws javax.servlet.ServletException, IOException {
        String[] ids = request.getParameterMap().get("id");
        if(ids!=null && ids.length>0){
            WidgetNode widgetNode1 = page.getWidgetNodeTree().getNodeMap().get(ids[0]);

        }
    }

    @Override
    public void afterHandle(Page page, HttpServletResponse response) throws Exception {

    }
}
