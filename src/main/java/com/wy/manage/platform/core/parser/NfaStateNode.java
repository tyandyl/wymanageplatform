package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by tianye on 2018/9/7.
 */
public class NfaStateNode {

    //状态编号
    private int stateNum;

    private NfaState state;

    private EdgeLine[] edgeLines=new EdgeLine[2];

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
