package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.action.Action;

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

    private Action action;
    //objectId
    private int objectId;

    private String belongRegular;

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

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getBelongRegular() {
        return belongRegular;
    }

    public void setBelongRegular(String belongRegular) {
        this.belongRegular = belongRegular;
    }
}
