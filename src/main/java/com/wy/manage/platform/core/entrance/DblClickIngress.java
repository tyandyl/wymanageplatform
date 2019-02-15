package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.bean.DblClickResult;
import com.wy.manage.platform.core.bean.ResultType;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.FileTools;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.TagType;
import com.wy.manage.platform.core.widget.WidgetNode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by tianye
 */
public class DblClickIngress extends Ingress{

    public void handleEx( HttpServletRequest request) throws javax.servlet.ServletException, IOException {

    }

    public void handle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            beforeHandle(request);
            DblClickResult dblClickResult=new DblClickResult();
            String[] ids = request.getParameterMap().get("id");
            if(ids!=null && ids.length>0){
                WidgetNode widgetNode1 = page.getWidgetNodeTree().getNodeMap().get(ids[0]);
                if(widgetNode1==null){
                    ExceptionTools.ThrowException("没有获取当前控件");
                }
                WidgetNode widgetNode=null;
                while (!widgetNode1.getData().isRemovable()){
                    widgetNode=widgetNode1;
                    widgetNode1=widgetNode1.getParentNode();
                }
                if(widgetNode!=null){
                    dblClickResult.setCode(ResultType.SUCCESS.getCode());
                    dblClickResult.setTagName(widgetNode.getData().getTagType().getName());
                    dblClickResult.setWd(widgetNode.getCode());
                }else {
                    dblClickResult.setCode(ResultType.FAILED.getCode());
                    dblClickResult.setMessage("没有获取当前控件");
                }


            }else {
                dblClickResult.setCode(ResultType.FAILED.getCode());
                dblClickResult.setMessage("没有获取当前控件");
            }

            response.setHeader("content-type", "text/html;charset=UTF-8");//注意是分号，不能是逗号
            OutputStream out = response.getOutputStream();
            String strPage = JSONObject.toJSONString(dblClickResult);
            out.write(strPage.getBytes());


        }catch (Exception e){
            System.out.println(e);
        }

    }

    @Override
    public void afterHandle( HttpServletResponse response) throws Exception {

    }
}
