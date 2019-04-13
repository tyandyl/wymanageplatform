package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.entrance.Context;
import com.wy.manage.platform.core.entrance.TableListIngress;
import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.FileTools;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                //afterLoadPage(getParamResult(), page);
                break;
            case EDIT_WIDGET:
                page=loadPage(getParamResult());
                //afterLoadPage(getParamResult(), page);
                break;
            default:
        }
    }

    public void afterLoadPage(WidgetModelParamResult widgetModelParamResult,Page page){
        //朝向资源是不能传递的，如我点击面板弹出窗口，这个窗口的所有控件绑定的都是这个朝向资源，
        //如果这个窗口上有一个按钮，我们点击这个按钮，弹出窗口，这个窗口的所有控件绑定的都是按钮这个朝向资源。
        //如果新窗口上还有按钮，且这个按钮有地址，点击这个按钮，我们设置的都是她所指定的朝向资源。
        String targetId = widgetModelParamResult.getParam().get("targetId");
        StringBuffer newTargetId=new StringBuffer();
        if(targetId!=null){
            String[] split = targetId.split(",");
            for(String m:split){
                newTargetId.append(m);
                System.out.println("目前朝向资源有:"+m);
                newTargetId.append(",");
                if(page!=null){
                    Map<String, WidgetNode> nodeMap = page.getWidgetNodeTree().getNodeMap();
                    if(nodeMap!=null){
                        WidgetNode widgetNode = nodeMap.get(m);
                        if(widgetNode!=null){
                            Set<String> register = widgetNode.getData().getRegisterParam().getTargetParam();
                            if(register!=null && register.size()>0){
                                for(String u:register){
                                    if(!newTargetId.toString().contains(u)){
                                        newTargetId.append(u);
                                        newTargetId.append(",");
                                        System.out.println("目前朝向资源有:"+u);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        if(newTargetId.toString().length()>0){
            widgetModelParamResult.getParam().put("targetId",newTargetId.toString());
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

    public void  handleEdit(WidgetModelParamResult result,Page page)throws Exception{
        String id = result.getParam().get("id");
        if(StringUtils.isBlank(id)){
            ExceptionTools.ThrowException("编辑状态不允许没有ID资源");
        }
        WidgetNode widgetNode1 = page.getWidgetNodeTree().getNodeMap().get(id);
        if(widgetNode1==null){
            ExceptionTools.ThrowException("编辑状态不允许没有ID节点");
        }
        Set<String> targetParam = widgetNode1.getData().getRegisterParam().getTargetParam();
        if(targetParam==null && targetParam.size()==0){
            ExceptionTools.ThrowException("编辑状态不允许没有目标资源");
        }
        String[] split = (String[])targetParam.toArray(new String[0]);
        List<List<CurWidget>> lists=new ArrayList<List<CurWidget>>();
        for(int i=0;i<split.length;i++){
            if(split[i].trim().equalsIgnoreCase("")){
                continue;
            }
            WidgetNode widgetNode = page.getWidgetNodeTree().getNodeMap().get(split[i]);
            if(widgetNode==null){
                ExceptionTools.ThrowException("编辑状态的目标资源为空");
            }
            WidgetNode widgetNodeEx=widgetNode;
            List<CurWidget> curWidgets=new ArrayList<CurWidget>();
            BlockType blockType = widgetNode.getData().getBlockType();
            if(BlockType.TABLE_PANEL==blockType){
                TablePanelFactory.addTableTd(widgetNode,result,page);
            }else if(BlockType.TABLE_LIST==blockType){
                TableListFactory.addTableTr(widgetNode);
            }

            page.fillNodeEX(widgetNodeEx,curWidgets);
            lists.add(curWidgets);
            result.getResult().setResult(lists);
        }
    }

    public boolean editExecute()throws Exception{
        handlePage();
        handleEdit(getParamResult(), page);

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
