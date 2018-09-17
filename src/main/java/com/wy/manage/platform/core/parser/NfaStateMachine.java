package com.wy.manage.platform.core.parser;

import java.util.List;

/**
 * Created by tianye on 2018/9/7.
 */
public class NfaStateMachine {
    private final int NFA_MAX_NUM=256;
    //一个整体，开始只能指向别人，别人不能指向他
    public NfaStateNode startNode;
    //一个整体，结束只能被指向，他不能指向别人
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
