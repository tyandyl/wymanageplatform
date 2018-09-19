package com.wy.manage.platform.core.parser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tianye
 */
public class EdgeLine implements Serializable {
    private static final long serialVersionUID = 123277793237844725L;
    //输入类型
    public EdgeInputType edgeInputType;
    //可以输入的字符集，也可以是单个字符，如果是ε，则为空
    public List<Character> edgeAllowInputGather;
    //下一个节点
    public NfaStateNode next;
    //对输入值的要求,大于等于几
    public int least=-1;
    //对输入值的要求,小于等于几
    public int max=-1;

    public int useNum=0;

    public EdgeType edgeType=EdgeType.NO_PASSED;


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

    public int getLeast() {
        return least;
    }

    public void setLeast(int least) {
        this.least = least;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getUseNum() {
        return useNum;
    }

    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }

    public EdgeType getEdgeType() {
        return edgeType;
    }

    public void setEdgeType(EdgeType edgeType) {
        this.edgeType = edgeType;
    }
}
