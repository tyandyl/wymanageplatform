package com.wy.manage.platform.core.entrance;

import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.widget.WidgetNode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tianye13 on 2019/3/1.
 */
public class MoveClickIngress extends Ingress{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

//    public void handleEx( HttpServletRequest request) throws javax.servlet.ServletException, IOException {
//
//    }
//
//    public void handle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        try {
//            beforeHandle(request);
//            DblClickResult dblClickResult=new DblClickResult();
//            String[] ids = request.getParameterMap().get("id");
//            if(ids!=null && ids.length>0){
//                WidgetNode widgetNode1 = page.getWidgetNodeTree().getNodeMap().get(ids[0]);
//                if(widgetNode1==null){
//                    ExceptionTools.ThrowException("没有获取当前控件");
//                }
//                WidgetNode widgetNode=null;
//                while (!widgetNode1.getData().isRemovable()){
//                    widgetNode=widgetNode1;
//                    widgetNode1=widgetNode1.getParentNode();
//                }
//                if(widgetNode!=null){
//                    dblClickResult.setCode(ResultType.SUCCESS.getCode());
//                    dblClickResult.setTagName(widgetNode.getData().getTagType().getName());
//                    dblClickResult.setWd(widgetNode.getCode());
//                }else {
//                    dblClickResult.setCode(ResultType.FAILED.getCode());
//                    dblClickResult.setMessage("没有获取当前控件");
//                }
//
//
//            }else {
//                dblClickResult.setCode(ResultType.FAILED.getCode());
//                dblClickResult.setMessage("没有获取当前控件");
//            }
//
//            response.setHeader("content-type", "text/html;charset=UTF-8");//注意是分号，不能是逗号
//            OutputStream out = response.getOutputStream();
//            String strPage = JSONObject.toJSONString(dblClickResult);
//            out.write(strPage.getBytes());
//
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
//
//    }
//
//    @Override
//    public void afterHandle( HttpServletResponse response) throws Exception {
//
//    }
}

