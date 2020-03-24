package com.wy.manage.platform.core.widget;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by tianye13 on 2019/1/9.
 */
public class WidgetNodeTree implements Serializable{

    private static final long serialVersionUID = 2041435464751839981L;
    private WidgetNode root;

    private Map<String,WidgetNode> nodeMap=new HashMap<String, WidgetNode>();

    private Stack<WidgetNode> newestNoClosed=null;

    public WidgetNode getRoot() {
        return root;
    }

    public void setRoot(WidgetNode root) {
        this.root = root;
    }

    public Stack<WidgetNode> getNewestNoClosed() {
        return newestNoClosed;
    }

    public void setNewestNoClosed(Stack<WidgetNode> newestNoClosed) {
        this.newestNoClosed = newestNoClosed;
    }

    public Map<String, WidgetNode> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<String, WidgetNode> nodeMap) {
        this.nodeMap = nodeMap;
    }
}
