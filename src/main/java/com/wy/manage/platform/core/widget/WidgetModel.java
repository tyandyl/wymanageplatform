package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.entrance.Context;
import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.FileTools;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by tianye
 */
public abstract class WidgetModel {

    private Page page=null;
    private WidgetModelParamResult paramResult=null;

    private BasicModel htmlModel=null;

    public WidgetModel init(HttpServletRequest request)throws Exception{
        paramResult=new WidgetModelParamResult(new JParam(request.getParameterMap()),request);
        handleParamMap( paramResult);
        handleType(paramResult);
        if(paramResult.getHandleType()!=null){
            return this;
        }
        return null;

    }

    public void handleType(WidgetModelParamResult paramResult){
        String[] handleTypes = paramResult.getJParam().get("handleType");
        if(handleTypes!=null){
            HandleType handleType = HandleType.getHandleType(Integer.valueOf(handleTypes[0]));
            if(handleType !=null){
                paramResult.setHandleType(handleType);
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

        String[] targetIds = paramResult.getJParam().get("targetId");
        if(targetIds!=null){
            paramResult.getParam().put("targetId",targetIds[0]);
        }
    }

    public final boolean add()throws Exception{
        if(!this.beforeAdd(paramResult)){
            return false;
        }
        if(addExecute()){
            return true;
        }
        return false;
    }

    public final boolean edit()throws Exception{
        if(!this.beforeEdit(paramResult)){
            return false;
        }
        if(editExecute()){
            return true;
        }
        return false;
    }

    public void handlePage(){
        switch (this.getParamResult().getHandleType()){
            case NEW_PAGE:
                HttpSession session = paramResult.getRequest().getSession(true);
                session.setMaxInactiveInterval(30*60);
                page=new Page();
                Context.put(session.getId(),page);
                break;
            case NEW_WIDGET:
                page=loadPage(getParamResult());
                break;
            case EDIT_WIDGET:
                page=loadPage(getParamResult());
                break;
            default:
        }
    }

    public boolean addExecute()throws Exception{
        handlePage();

        htmlModel=initLoadHtmlModel();
        htmlModel.defineAction();
        StringBuffer stringBuffer = FileTools.getContent(htmlModel.getContentAddress(),true);
        htmlModel.execute(stringBuffer.toString(),this);
        return true;
    }

    public boolean editExecute()throws Exception{
        handlePage();
        String targetId = this.getParamResult().getParam().get("targetId");
        if(StringUtils.isBlank(targetId)){
            ExceptionTools.ThrowException("编辑状态不允许没有目标资源");
        }
        WidgetNode widgetNode = page.getWidgetNodeTree().getNodeMap().get(targetId);
        if(widgetNode==null){
            ExceptionTools.ThrowException("编辑状态的目标资源为空");
        }
        BlockType blockType = widgetNode.getData().getBlockType();
        if(BlockType.TABLE_PANEL==blockType){
            int i=0;
            while (widgetNode.getChildNodes().size()>0){
                i++;
                if(widgetNode.getData().getTagType()==TagType.TD){
                    widgetNode.getData().setOutValue("666");
                    break;
                }
                if(i==5){
                    break;
                }
            }
        }
        return true;
    }

    public WidgetModel execute()throws Exception{
        HandleType handleType = paramResult.getHandleType();
        switch (handleType){
            case EDIT_WIDGET:
                edit();
                break;
            case NEW_PAGE:
                add();
                break;
            case NEW_WIDGET:
                add();
                break;
        }
        return this;
    }





    public abstract BasicModel initLoadHtmlModel();


    public abstract Page loadPage(WidgetModelParamResult widgetModelParamResult);

    public boolean beforeAdd(WidgetModelParamResult paramResult){
        return true;
    }

    public boolean beforeEdit(WidgetModelParamResult paramResult){
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
