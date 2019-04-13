package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.Properties;
import com.wy.manage.platform.core.parser.CssBag;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/28.
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -2602415919744812735L;

    private DocType docType;

    private List<Meta> metas=new ArrayList<Meta>();

    private List<Script> scripts=new ArrayList<Script>();

    private List<Link> links=new ArrayList<Link>();

    private Map<String,CssBag> cssMaps=new HashMap<String,CssBag>();

    private WidgetNodeTree widgetNodeTree=new WidgetNodeTree();


    public String toView(){
        StringBuffer view =new StringBuffer();
        DocType docType = this.getDocType();
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
        List<Meta> metas = this.getMetas();
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
        List<Script> scripts = this.getScripts();
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
        List<Link> links = this.getLinks();
        if(links!=null && links.size()>0) {
            StringBuffer linkStr=new StringBuffer();
            for (Link link : links) {
                linkStr.append("<link rel=\"stylesheet\" style=\"text/css\" href=\"");
                linkStr.append(link.getHref());
                linkStr.append("\" />");
            }
            view.append(linkStr);
        }
        view.append("</head>");
        view.append("<body>");

        WidgetNodeTree widgetNodeTree = this.getWidgetNodeTree();
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
        view.append(" wd='"+data.getCode()+"' ");
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


    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public List<Meta> getMetas() {
        return metas;
    }
    public void addMeta(Meta meta){
        this.metas.add(meta);
    }

    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }

    public List<Script> getScripts() {
        return scripts;
    }

    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
    }

    public void addScript(Script script){
        this.scripts.add(script);
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(Link link){
        this.links.add(link);
    }

    public Map<String, CssBag> getCssMaps() {
        return cssMaps;
    }

    public void setCssMaps(Map<String, CssBag> cssMaps) {
        this.cssMaps = cssMaps;
    }

    public WidgetNodeTree getWidgetNodeTree() {
        return widgetNodeTree;
    }

    public void setWidgetNodeTree(WidgetNodeTree widgetNodeTree) {
        this.widgetNodeTree = widgetNodeTree;
    }

}
