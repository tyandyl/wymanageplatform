package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.entrance.Context;
import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.utils.FileTools;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public abstract class WidgetModel {

    private Page page=null;
    private WidgetModelParamResult paramResult=null;

    private BasicModel htmlModel=null;

    public WidgetModel init(HttpServletRequest request)throws Exception{
        paramResult=new WidgetModelParamResult(new JParam(request.getParameterMap()),request);
        handleType(paramResult);
        handleParamMap( paramResult);
        if(paramResult.getAddType()!=null){
            return this;
        }
        return null;

    }

    public void handleType(WidgetModelParamResult paramResult){
        String[] handleTypes = paramResult.getJParam().get("handleType");
        if(handleTypes!=null){
            AddType addType = AddType.getAddType(Integer.valueOf(handleTypes[0]));
            if(addType!=null){
                paramResult.setAddType(addType);
            }else {
                System.out.println("创建类型不匹配");
            }
        }else {
            System.out.println("创建类型不存在");
        }
    }

    public void handleParamMap(WidgetModelParamResult paramResult){
        String[] ids = paramResult.getJParam().get("id");
        if(ids!=null){
            paramResult.getParam().put("id",ids[0]);
        }

        String[] tops = paramResult.getJParam().get("top");
        if(tops!=null){
            paramResult.getParam().put("top",tops[0]);
        }

        String[] lefts = paramResult.getJParam().get("left");
        if(lefts!=null){
            paramResult.getParam().put("left",lefts[0]);
        }

        String[] positions = paramResult.getJParam().get("position");
        if(positions!=null){
            paramResult.getParam().put("position",positions[0]);
        }
    }

    public final boolean add()throws Exception{
        if(!this.beforeAdd(paramResult)){
            return false;
        }
        if(addExecute(paramResult)){
            return true;
        }
        return false;
    }

    public boolean addExecute(WidgetModelParamResult paramResult)throws Exception{
        switch (this.getParamResult().getAddType()){
            case PAGE:
                HttpSession session = paramResult.getRequest().getSession(true);
                session.setMaxInactiveInterval(30*60);
                page=new Page();
                Context.put(session.getId(),page);
                break;
            case WIDGET:
                page=loadPage(getParamResult());
                break;
            default:
        }

        htmlModel=initLoadHtmlModel();
        htmlModel.defineAction();
        StringBuffer stringBuffer = FileTools.getContent(htmlModel.getContentAddress(),true);
        htmlModel.execute(stringBuffer.toString(),this);
        return true;
    }





    public abstract BasicModel initLoadHtmlModel();


    public abstract Page loadPage(WidgetModelParamResult widgetModelParamResult);

    public boolean beforeAdd(WidgetModelParamResult paramResult){
        return true;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public WidgetModelParamResult getParamResult() {
        return paramResult;
    }

    public void setParamResult(WidgetModelParamResult paramResult) {
        this.paramResult = paramResult;
    }

    public BasicModel getHtmlModel() {
        return htmlModel;
    }

    public void setHtmlModel(BasicModel htmlModel) {
        this.htmlModel = htmlModel;
    }
}
