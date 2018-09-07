package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by tianye on 2018/9/7.
 */
public class NfaStateNode {

    public NfaStateNode[] next=new NfaStateNode[2];

    //状态编号
    public int stateNum;

    public NfaState state;

    public EdgeLine[] edgeLines=new EdgeLine[2];

    public NfaStateNode[] getNext() {
        return next;
    }

    public void setNext(NfaStateNode[] next) {
        this.next = next;
    }

    public int getStateNum() {
        return stateNum;
    }

    public void setStateNum(int stateNum) {
        this.stateNum = stateNum;
    }

    public NfaState getState() {
        return state;
    }

    public void setState(NfaState state) {
        this.state = state;
    }

    public EdgeLine[] getEdgeLines() {
        return edgeLines;
    }

    public void setEdgeLines(EdgeLine[] edgeLines) {
        this.edgeLines = edgeLines;
    }
}
