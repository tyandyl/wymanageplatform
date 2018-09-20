package com.wy.manage.platform.core.widget;

import java.io.Serializable;

/**
 * Created by tianye13 on 2018/9/20.
 */
public class RelativeFlow implements IFlow, Serializable {
    private static final long serialVersionUID = -4621715633334121527L;
    /**
     * 向下偏移
     */
    public int top;

    /**
     * 向右偏移
     */
    public int left;

    /**
     * 向左偏移
     */
    public int right;

    /**
     * 向上偏移
     */
    public int bottom;

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }
}
