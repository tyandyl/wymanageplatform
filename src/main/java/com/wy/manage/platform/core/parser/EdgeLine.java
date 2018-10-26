package com.wy.manage.platform.core.parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye
 * nfa节点的连接线
 */
public class EdgeLine implements Serializable {
    private static final long serialVersionUID = 123277793237844725L;
    //输入类型
    public EdgeInputType edgeInputType;
    //可以输入的字符集，也可以是单个字符，如果是ε，则为空
    public List<Character> edgeAllowInputGather=new ArrayList<Character>();
    //下一个节点
    public NfaStateNode next;

    //是否经过，遍历的时候使用，防止溢出
    public boolean isPassed;

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public NfaStateNode getNext() {
        return next;
    }

    public void setNext(NfaStateNode next) {
        this.next = next;
    }

    public EdgeInputType getEdgeInputType() {
        return edgeInputType;
    }

    public void setEdgeInputType(EdgeInputType edgeInputType) {
        this.edgeInputType = edgeInputType;
    }

    public List<Character> getEdgeAllowInputGather() {
        return edgeAllowInputGather;
    }

    public void setEdgeAllowInputGather(List<Character> edgeAllowInputGather) {
        this.edgeAllowInputGather = edgeAllowInputGather;
    }
}
