package com.wy.manage.platform.core.widget;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by tianye13 on 2019/1/9.
 */
public class WidgetNodeTree implements Serializable{

    private static final long serialVersionUID = 2041435464751839981L;
    private WidgetNode root;

    private Stack<WidgetNode> newestNoClosed=new Stack<WidgetNode>();

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
}
