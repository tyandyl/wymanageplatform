package com.wy.manage.platform.core;

/**
 * Created by tianye on 2018/6/27.
 * 定位
 */
public class PositionFlow implements Flow{

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
