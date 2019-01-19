package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.Properties;
import com.wy.manage.platform.core.parser.CssBag;

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

    private String baseAddress;

    private String htmlAddress;

    private String htmlName;

    private List<Meta> metas=new ArrayList<Meta>();

    private List<Script> scripts=new ArrayList<Script>();

    private List<Link> links=new ArrayList<Link>();

    private StringBuffer str=new StringBuffer();

    private StringBuffer strStyle=new StringBuffer();

    private Map<String,CssBag> cssMaps=new HashMap<String,CssBag>();

    private WidgetNodeTree widgetNodeTree=new WidgetNodeTree();


    private Map<String, String[]> paramMap=new HashMap<String, String[]>();

    private String styleType;

    private boolean firstIsCame=false;

    public boolean isFirstIsCame() {
        return firstIsCame;
    }

    public void setFirstIsCame(boolean firstIsCame) {
        this.firstIsCame = firstIsCame;
    }

    public String getStyleType() {
        return styleType;
    }

    public void setStyleType(String styleType) {
        this.styleType = styleType;
    }

    public Map<String, String[]> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String[]> paramMap) {
        this.paramMap = paramMap;
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

    public StringBuffer getStr() {
        return str;
    }

    public void setStr(StringBuffer str) {
        this.str = str;
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

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    public String getHtmlAddress() {
        return htmlAddress;
    }

    public void setHtmlAddress(String htmlAddress) {
        this.htmlAddress = htmlAddress;
    }

    public String getHtmlName() {
        return htmlName;
    }

    public void setHtmlName(String htmlName) {
        this.htmlName = htmlName;
    }

    public StringBuffer getStrStyle() {
        return strStyle;
    }

    public void setStrStyle(StringBuffer strStyle) {
        this.strStyle = strStyle;
    }
}
