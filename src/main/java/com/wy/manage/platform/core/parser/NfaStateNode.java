package com.wy.manage.platform.core.parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by tianye
 * nfa节点，每个节点只能有两条连接线
 */
public class NfaStateNode implements Serializable{
    private static final long serialVersionUID = -6105929014841964509L;
    //状态编号
    private String stateNum;

    private NfaState state;

    private EdgeLine[] edgeLines=new EdgeLine[2];

    private RelevanceHandle handle;
    private String handleName;

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public String getStateNum() {
        return stateNum;
    }

    public void setStateNum(String stateNum) {
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

    public RelevanceHandle getHandle() {
        return handle;
    }

    public void setHandle(RelevanceHandle handle) {
        this.handle = handle;
    }

}
