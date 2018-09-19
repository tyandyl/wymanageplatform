package com.wy.manage.platform.core.parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by tianye
 */
public class NfaStateNode implements Serializable{
    private static final long serialVersionUID = -6105929014841964509L;
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
