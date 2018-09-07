package com.wy.manage.platform.core.parser;

/**
 * Created by tianye on 2018/9/7.
 */
public class NfaStateMachine {
    private final int NFA_MAX_NUM=256;
    public NfaStateNode startNode;
    public NfaStateNode endNode;

    public NfaStateNode getStartNode() {
        return startNode;
    }

    public void setStartNode(NfaStateNode startNode) {
        this.startNode = startNode;
    }

    public NfaStateNode getEndNode() {
        return endNode;
    }

    public void setEndNode(NfaStateNode endNode) {
        this.endNode = endNode;
    }
}
