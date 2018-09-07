package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by tianye on 2018/9/7.
 */
public class NfaManager {

    /**
     * 创建最简单状态机
     * @return
     */
    public static NfaStateMachine createSimplestNfaStateMachine(){
        NfaStateMachine nfaStateMachine=new NfaStateMachine();
        nfaStateMachine.setStartNode(createStartNode());
        nfaStateMachine.setEndNode(createEndNode());
        NfaStateNode[] next = nfaStateMachine.getStartNode().getNext();
        next[0]=nfaStateMachine.getEndNode();
        EdgeLine[] edgeLines = nfaStateMachine.getStartNode().getEdgeLines();

        EdgeLine edgeLine=new EdgeLine();
        edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLines[0]=edgeLine;
        return nfaStateMachine;
    }


    /**
     * 创建单字符状态机
     * @param i 单个字符值
     * @return
     */
    public static NfaStateMachine createSingleCharacterNfaStateMachine(int i){
        NfaStateMachine simplestNfaStateMachine = createSimplestNfaStateMachine();
        EdgeLine[] edgeLines = simplestNfaStateMachine.getStartNode().getEdgeLines();
        EdgeLine edgeLine = edgeLines[0];
        edgeLine.setEdgeInputType(EdgeInputType.CHARACTER_ALONE);
        List<Character> list=new ArrayList<Character>();
        list.add((char)i);
        edgeLine.setEdgeAllowInputGather(list);
        return simplestNfaStateMachine;
    }

    /**
     * 创建字符集状态机
     * @param str
     * @return
     */
    public static NfaStateMachine createCharacterRepertoireNfaStateMachine(List<Character> str){
        NfaStateMachine simplestNfaStateMachine = createSimplestNfaStateMachine();
        EdgeLine[] edgeLines = simplestNfaStateMachine.getStartNode().getEdgeLines();
        EdgeLine edgeLine = edgeLines[0];
        edgeLine.setEdgeInputType(EdgeInputType.CHARACTER_REPERTOIRE);
        edgeLine.setEdgeAllowInputGather(str);
        return simplestNfaStateMachine;
    }

    /**
     * 创建连接&
     * @param var1
     * @param var2
     * @return
     */
    public static NfaStateMachine createLinkNfaStateMachine(NfaStateMachine var1,NfaStateMachine var2){
        //获取var1的结束节点
        NfaStateNode var1EndNode = var1.getEndNode();
        //设置var1结束节点的连接
        NfaStateNode[] var1EndNodeNext = var1EndNode.getNext();
        var1EndNodeNext[0]=var2.getStartNode();
        //设置var1结束节点的状态，由end变成proceed
        var1EndNode.setState(NfaState.PROCEED);
        //设置var2开始节点的状态，由start变成proceed
        var2.getStartNode().setState(NfaState.PROCEED);

        EdgeLine edgeLine=new EdgeLine();
        edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
        EdgeLine[] edgeLines = var1EndNode.getEdgeLines();
        edgeLines[0]=edgeLine;

        return var1;
    }

    /**
     * 创建*，至少重复0次
     * @param var
     * @return
     */
    public static NfaStateMachine createOrNfaStateMachine(NfaStateMachine var){
        NfaStateNode endNode = var.getEndNode();
        NfaStateNode[] next = endNode.getNext();
        next[0]=var.getStartNode();
        EdgeLine[] edgeLines = endNode.getEdgeLines();
        EdgeLine edgeLine=new EdgeLine();
        edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLines[0]=edgeLine;
        return var;
    }


    public static NfaStateNode createStartNode(){
        NfaStateNode nfaStateNode=new NfaStateNode();
        nfaStateNode.setState(NfaState.START);
        return nfaStateNode;
    }

    public static NfaStateNode createEndNode(){
        NfaStateNode nfaStateNode=new NfaStateNode();
        nfaStateNode.setState(NfaState.END);
        return nfaStateNode;
    }
}
