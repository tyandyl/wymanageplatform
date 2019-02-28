package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.utils.FileTools;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by tianye
 */
public abstract class WidgetModel {

    private Page page=null;
    private WidgetModelParamResult paramResult=null;

    private BasicModel htmlModel=null;

    public final boolean add(AddType addType,JParam jParam)throws Exception{
        if(!this.beforeAdd(addType,jParam)){
            return false;
        }
        initParamResult(jParam,addType);
        addExecute(addType);
        return true;
    }

    public boolean addExecute(AddType addType)throws Exception{
        switch (addType){
            case PAGE:
                page=new Page();
                break;
            case WIDGET:
                loadPage(getParamResult());
                break;
            default:
        }

        htmlModel=initLoadHtmlModel();
        htmlModel.defineAction();
        StringBuffer stringBuffer = FileTools.getContent(htmlModel.getContentAddress(),true);
        htmlModel.execute(stringBuffer.toString(),this);
        return true;
    }

    public String toView(){
        StringBuffer view =new StringBuffer();
        DocType docType = this.getPage().getDocType();
        switch (docType){
            case HTML5:
                view.append("<!DOCTYPE html>");
            break;
            case TRANSITIONAL:
                view.append("<!DOCTYPE html>");
                break;
        }
        view.append("<html>");
        view.append("<head>");
        List<Meta> metas = this.getPage().getMetas();
        if(metas!=null && metas.size()>0){
            StringBuffer metaStr=new StringBuffer();
            for(Meta meta:metas){
                metaStr.append("<meta ");
                metaStr.append("http-equiv=\"");
                metaStr.append(meta.getHttp_equiv()+"\" ");
                metaStr.append("content=\"");
                metaStr.append(meta.getContent()+"\">");
            }
            view.append(metaStr);
        }
        List<Script> scripts = this.getPage().getScripts();
        if(scripts!=null && scripts.size()>0) {
            StringBuffer scriptStr=new StringBuffer();
            for (Script script : scripts) {
                scriptStr.append("<script type=\"");
                scriptStr.append(script.getType());
                scriptStr.append("\" src=\"");
                scriptStr.append(script.getSrc());
                scriptStr.append("\">");
                scriptStr.append("</script>");
            }
            view.append(scriptStr);
        }
        List<Link> links = this.getPage().getLinks();
        if(links!=null && links.size()>0) {
            StringBuffer linkStr=new StringBuffer();
            for (Link link : links) {
                StringBuffer str=new StringBuffer();
                str.append("<link rel=\"stylesheet\" style=\"text/css\" href=\"");
                str.append(link.getHref());
                str.append("\" />");
            }
            view.append(linkStr);
        }
        view.append("</head>");
        view.append("<body>");

        WidgetNodeTree widgetNodeTree = this.getPage().getWidgetNodeTree();
        WidgetNode root = widgetNodeTree.getRoot();
        fillNode( root, view);
        view.append("</body>");
        view.append("</html>");
        return view.toString();
    }

    public void fillNode(WidgetNode node,StringBuffer view){
        Widget data = node.getData();
        TagType tagType = data.getTagType();
        view.append("<"+tagType.getName());
        String curPros = data.getCurPros();
        if(StringUtils.isNotBlank(curPros)){
            view.append(" style=\"");
            view.append(curPros);
            if(node.isFirstClosed()){
                view.append("\" />");
            }else {
                view.append("\" >");
            }
        }
        List<WidgetNode> childNodes = node.getChildNodes();
        if(childNodes!=null && childNodes.size()>0){
            for(WidgetNode node1:childNodes){
                fillNode( node1, view);
            }
        }
        if(!node.isFirstClosed()){
            view.append("</"+tagType.getName()+">");
        }


    }



    public abstract BasicModel initLoadHtmlModel();


    public abstract void loadPage(WidgetModelParamResult widgetModelParamResult);

    public boolean beforeAdd(AddType addType,JParam param){
        return true;
    }

    public void initParamResult(JParam jParam,AddType addType){
        paramResult=new WidgetModelParamResult();
        paramResult.setJParam(jParam);
        paramResult.setAddType(addType);
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
