package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import javax.print.DocFlavor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
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
        edgeLine.setEdgeInputType(EdgeInputType.CHARACTER_REPERTOIRE);
        List<Character> list=new ArrayList<Character>();
        list.add((char)i);
        edgeLine.setEdgeAllowInputGather(list);
        return simplestNfaStateMachine;
    }

    /**
     * 创建字符集状态机
     * @param str
     * @return
     * @throws Exception
     */
    public static NfaStateMachine createCharacterRepertoireNfaStateMachine(List<Character> str)throws Exception{
        NfaStateMachine simplestNfaStateMachine = createSimplestNfaStateMachine(true);
        EdgeLine edgeLine =simplestNfaStateMachine.getStartNode().getEdgeLines()[0];
        edgeLine.setEdgeInputType(EdgeInputType.CHARACTER_REPERTOIRE);
        edgeLine.setEdgeAllowInputGather(str);
        return simplestNfaStateMachine;
    }

    public static NfaStateMachine createCharacterRepertoireNfaStateMachine(NfaStateMachine var1,int i)throws Exception{
        NfaStateNode startNode = var1.getStartNode();
        EdgeLine[] edgeLines = startNode.getEdgeLines();
        List<Character> edgeAllowInputGather = edgeLines[0].getEdgeAllowInputGather();
        edgeAllowInputGather.add((char)i);
        return var1;
    }

    /**
     * 创建任何字符状态机
     * @return
     */
    public static NfaStateMachine createAnyCharacterRepertoireNfaStateMachine()throws Exception{
        NfaStateMachine simplestNfaStateMachine = createSimplestNfaStateMachine(true);
        EdgeLine edgeLine =simplestNfaStateMachine.getStartNode().getEdgeLines()[0];
        List<Character> edgeAllowInputGather = edgeLine.getEdgeAllowInputGather();
        for(int i=0;i<127;i++){
            edgeAllowInputGather.add((char)i);
        }
        edgeLine.setEdgeInputType(EdgeInputType.CHARACTER_REPERTOIRE);
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

        var1.setEndNode(var2.getEndNode());
        return var1;
    }

    /**
     *创建或者OR
     * @param var1
     * @param var2
     * @return
     * @throws Exception
     */
    public static NfaStateMachine createOrNfaStateMachine(NfaStateMachine var1,NfaStateMachine var2)throws Exception{
        NfaStateMachine simplestNfaStateMachine = createSimplestNfaStateMachine(false);
        NfaStateNode startNode = simplestNfaStateMachine.getStartNode();
        NfaStateNode endNode = simplestNfaStateMachine.getEndNode();
        EdgeLine edgeLine=new EdgeLine();
        edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine.setNext(var1.getStartNode());

        EdgeLine[] edgeLines = startNode.getEdgeLines();

        assignArray(edgeLines, edgeLine);
        var1.getStartNode().setState(NfaState.PROCEED);

        EdgeLine edgeLine1=new EdgeLine();
        edgeLine1.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine1.setNext(var2.getStartNode());

        assignArray(edgeLines, edgeLine1);
        var2.getStartNode().setState(NfaState.PROCEED);

        EdgeLine edgeLine2=new EdgeLine();
        edgeLine2.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine2.setNext(endNode);

        EdgeLine[] edgeLines1 = var1.getEndNode().getEdgeLines();
        assignArray(edgeLines1, edgeLine2);
        var1.getEndNode().setState(NfaState.PROCEED);

        EdgeLine edgeLine3=new EdgeLine();
        edgeLine3.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine3.setNext(endNode);

        EdgeLine[] edgeLines2 = var2.getEndNode().getEdgeLines();
        assignArray(edgeLines2, edgeLine3);
        var2.getEndNode().setState(NfaState.PROCEED);
        return simplestNfaStateMachine;
    }

    /**
     * 创建*，至少重复0次
     * @param var
     * @return
     */
    public static NfaStateMachine createRepetitionStarNfaStateMachine(NfaStateMachine var)throws Exception{
        EdgeLine edgeLine=new EdgeLine();
        edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine.setEdgeType(EdgeType.MAYBE);
        edgeLine.setNext(var.getStartNode());

        NfaStateNode endNode = var.getEndNode();
        EdgeLine[] edgeLines = endNode.getEdgeLines();
        assignArray(edgeLines, edgeLine);
        traverse( var.getStartNode(),var.getEndNode());
        return var;
    }

    public static void traverse(NfaStateNode startNode,NfaStateNode endNode){
        if(startNode!=null){
            if(startNode.getEdgeLines()[0]!=null){
                if(startNode.getEdgeLines()[0].getNext()!=endNode){
                    traverse(startNode.getEdgeLines()[0].getNext(),endNode);
                }
                startNode.getEdgeLines()[0].setEdgeType(EdgeType.MAYBE);
            }
            if(startNode.getEdgeLines()[1]!=null){
                if(startNode.getEdgeLines()[1].getNext()!=endNode){
                    traverse(startNode.getEdgeLines()[1].getNext(),endNode);
                }
                startNode.getEdgeLines()[1].setEdgeType(EdgeType.MAYBE);
            }
        }
    }

    /**
     * 创建+，至少重复1次
     * @param var
     * @return
     */
    public static NfaStateMachine createRepetitionAddNfaStateMachine(NfaStateMachine var)throws Exception{
        NfaStateMachine nfaStateMachine = deepClone(var);
        NfaStateMachine repetitionStarNfaStateMachine = createRepetitionStarNfaStateMachine(var);
        NfaStateMachine linkNfaStateMachine = createLinkNfaStateMachine(nfaStateMachine, repetitionStarNfaStateMachine);
        return linkNfaStateMachine;
    }

    /**
     * 创建{n,m}，至少重复n次，最多重复m次
     * @param var
     * @param n
     * @param m
     * @return
     * @throws Exception
     */
    public static NfaStateMachine createRepetitionAddNumNfaStateMachine(NfaStateMachine var,int n,int m)throws Exception{
        EdgeLine edgeLine=new EdgeLine();
        NfaStateNode startNode = var.getStartNode();
        edgeLine.setEdgeInputType(EdgeInputType.NULL_GATHER);
        edgeLine.setNext(startNode);
        edgeLine.setLeast(n);
        edgeLine.setMax(m);

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
        assignArray(edgeLines1, edgeLine2);

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
            ExceptionTools.ThrowException("路径赋值失败");
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

    public static NfaStateMachine deepClone(NfaStateMachine var){
        ObjectOutputStream os = null;
        ObjectInputStream ois = null;
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(var);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            NfaStateMachine target = (NfaStateMachine)ois.readObject();
            return target;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ois.close();
                os.close();
            } catch (Exception e) {
                os = null;
                ois=null;
            }
        }
        return null;
    }
}
