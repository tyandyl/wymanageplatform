package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye on 2018/9/7.
 */
public class NfaManager {

    /**
     * 创建最简单状态机
     * @param isCreateEdgeLine
     * @return
     */
    public static NfaStateMachine createSimplestNfaStateMachine(boolean isCreateEdgeLine)throws Exception{
        NfaStateMachine nfaStateMachine=new NfaStateMachine();
        nfaStateMachine.setStartNode(createStartNode());
        nfaStateMachine.setEndNode(createEndNode());

        if(isCreateEdgeLine){
            EdgeLine[] edgeLines = nfaStateMachine.getStartNode().getEdgeLines();
            EdgeLine edgeLine=new EdgeLine();
            edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
            edgeLine.setNext(nfaStateMachine.getEndNode());
            assignArray(edgeLines, edgeLine);
        }

        return nfaStateMachine;
    }


    /**
     * 创建单字符状态机
     * @param i 单个字符值
     * @return
     */
    public static NfaStateMachine createSingleCharacterNfaStateMachine(int i)throws Exception{
        NfaStateMachine simplestNfaStateMachine = createSimplestNfaStateMachine(true);
        EdgeLine edgeLine = simplestNfaStateMachine.getStartNode().getEdgeLines()[0];
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
    public static NfaStateMachine createCharacterRepertoireNfaStateMachine(List<Character> str)throws Exception{
        NfaStateMachine simplestNfaStateMachine = createSimplestNfaStateMachine(true);
        EdgeLine edgeLine =simplestNfaStateMachine.getStartNode().getEdgeLines()[0];
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
    public static NfaStateMachine createLinkNfaStateMachine(NfaStateMachine var1,NfaStateMachine var2)throws Exception{
        EdgeLine edgeLine=new EdgeLine();
        edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine.setNext(var2.getStartNode());

        //获取var1的结束节点
        NfaStateNode var1EndNode = var1.getEndNode();
        //设置var1结束节点的连接
        EdgeLine[] edgeLines1 = var1EndNode.getEdgeLines();
        assignArray(edgeLines1, edgeLine);

        //设置var1结束节点的状态，由end变成proceed
        var1EndNode.setState(NfaState.PROCEED);
        //设置var2开始节点的状态，由start变成proceed
        var2.getStartNode().setState(NfaState.PROCEED);

        NfaStateMachine nfaStateMachine = packNewStartAndEndNode( var1,  var2);
        return nfaStateMachine;
    }

    /**
     * 创建*，至少重复0次
     * @param var
     * @return
     */
    public static NfaStateMachine createRepetitionStarNfaStateMachine(NfaStateMachine var)throws Exception{
        NfaStateMachine repetitionAddNfaStateMachine = createRepetitionAddNfaStateMachine( var);
        NfaStateNode startNode = repetitionAddNfaStateMachine.getStartNode();
        NfaStateNode endNode = repetitionAddNfaStateMachine.getEndNode();

        EdgeLine edgeLine=new EdgeLine();
        edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine.setNext(endNode);

        EdgeLine[] edgeLines = startNode.getEdgeLines();
        edgeLines[1]=edgeLine;
        return repetitionAddNfaStateMachine;
    }

    /**
     * 创建+，至少重复1次
     * @param var
     * @return
     */
    public static NfaStateMachine createRepetitionAddNfaStateMachine(NfaStateMachine var)throws Exception{
        EdgeLine edgeLine=new EdgeLine();
        edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine.setNext(var.getStartNode());

        NfaStateNode endNode = var.getEndNode();
        EdgeLine[] edgeLines = endNode.getEdgeLines();
        assignArray(edgeLines, edgeLine);
        NfaStateMachine nfaStateMachine = packNewStartAndEndNode( var,  null);
        return nfaStateMachine;
    }

    /**
     * 创建?，最多重复1次
     * @param var
     * @return
     * @throws Exception
     */
    public static NfaStateMachine createRepetitionQuestionMarkNfaStateMachine(NfaStateMachine var)throws Exception{
        NfaStateMachine nfaStateMachine = packNewStartAndEndNode( var,  null);
        return nfaStateMachine;
    }


    /**
     * 包装新的开始结束节点
     * @param var1
     * @param var2
     * @return
     */
    public static NfaStateMachine packNewStartAndEndNode(NfaStateMachine var1,NfaStateMachine var2)throws Exception{
        NfaStateMachine simplestNfaStateMachine = createSimplestNfaStateMachine(false);
        NfaStateNode startNode = simplestNfaStateMachine.getStartNode();
        NfaStateNode endNode = simplestNfaStateMachine.getEndNode();
        NfaStateNode startNode1 = var1.getStartNode();

        EdgeLine edgeLine=new EdgeLine();
        edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine.setNext(startNode1);
        EdgeLine[] edgeLines = startNode.getEdgeLines();
        assignArray(edgeLines, edgeLine);
        startNode1.setState(NfaState.PROCEED);

        NfaStateNode endNode1=null;
        if(var2!=null){
            endNode1 = var2.getEndNode();
        }else {
            endNode1 = var1.getEndNode();
        }
        EdgeLine edgeLine2=new EdgeLine();
        edgeLine2.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine2.setNext(endNode);
        endNode1.setState(NfaState.PROCEED);
        EdgeLine[] edgeLines1 = endNode1.getEdgeLines();
        assignArray(edgeLines1, edgeLine);

        return simplestNfaStateMachine;
    }

    /**
     * 赋值数组
     * @param edgeLines1
     * @param edgeLine
     * @throws Exception
     */
    public static void assignArray(EdgeLine[] edgeLines1,EdgeLine edgeLine)throws Exception{
        if(edgeLines1[0]==null){
            edgeLines1[0]=edgeLine;
        }else if(edgeLines1[1]==null){
            edgeLines1[1]=edgeLine;
        }else {
            throw new Exception();
        }
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
