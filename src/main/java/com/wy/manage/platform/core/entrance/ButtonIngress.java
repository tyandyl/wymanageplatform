package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.WidgetNode;
import com.wy.manage.platform.core.widget.WidgetNodeTree;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
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


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
