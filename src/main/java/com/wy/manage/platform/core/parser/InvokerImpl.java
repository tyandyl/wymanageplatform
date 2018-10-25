package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by tianye
 */
public class InvokerImpl implements Invoker {
    private String regularStr;
    private long sTid = 0L;
    private boolean isPrint = false;
    private RelevanceHandle handle;

    public InvokerImpl(String regularStr) {
        this.regularStr = regularStr;
    }


    public InvokerImpl relevance(RelevanceHandle handle){
        this.handle=handle;
        return this;
    }

    public InvokerImpl setIsPrint(boolean isPrint){
        this.isPrint=isPrint;
        return this;
    }


    public NfaStateMachine invoke() {
        NfaStateMachine t=null;
        try {
            this.sTid = System.currentTimeMillis();
            t=this.onInvoke(this.preInvoke(),handle);
        } catch (Exception e) {
        } finally {
        }
        return t;
    }

    private NfaStateMachine onInvoke(NfaStateMachine nfaStateMachine,RelevanceHandle handle) throws Exception{
        NfaStateNode endNode = nfaStateMachine.getEndNode();
        endNode.setHandle(handle);
        //traverse(nfaStateMachine.startNode, endNode,0);
        return nfaStateMachine;
    }

    public static void traverse(NfaStateNode startNode,NfaStateNode endNode,int i){
        if(startNode!=null){
            i++;
            if(startNode.getEdgeLines()[0]!=null){
                List<Character> edgeAllowInputGather = startNode.getEdgeLines()[0].getEdgeAllowInputGather();
                System.out.println("第"+i+"个节点的第1条边的输入是:"+(edgeAllowInputGather.size()==0?"空集":String.valueOf((Character[])edgeAllowInputGather.toArray())));
                if(startNode.getEdgeLines()[0].getNext()!=endNode){
                    traverse(startNode.getEdgeLines()[0].getNext(),endNode,i);
                }
            }
            if(startNode.getEdgeLines()[1]!=null){
                List<Character> edgeAllowInputGather = startNode.getEdgeLines()[1].getEdgeAllowInputGather();
                System.out.println("第"+i+"个节点的第2条边的输入是:"+(edgeAllowInputGather.size()==0?"空集":String.valueOf((Character[])edgeAllowInputGather.toArray())));
                if(startNode.getEdgeLines()[1].getNext()!=endNode){
                    traverse(startNode.getEdgeLines()[1].getNext(),endNode,i);
                }
            }
        }
    }

    private NfaStateMachine preInvoke() throws Exception {
        Stack<XContentItem> stack = RegularExpressionParser.parserCss(regularStr.toCharArray());
        if (stack.size() == 1) {
            XContentItem peek = stack.peek();
            NfaStateMachine nfaStateMachine = peek.getNfaStateMachine();
            if (this.isPrint) {
                System.out.println("开始解析正则表达式:" + regularStr);
            }
            return nfaStateMachine;
        } else {
            System.out.println("栈的长度是:" + stack.size());
            System.out.println("栈末级项的内容是:" + stack.peek().getIndex());
            ExceptionTools.ThrowException("解析失败,请检查");
        }
        return null;

    }

    private DfaContext handleMapFirst(NfaStateMachine var)throws Exception{
        final DfaContext context=new DfaContext();
        context.setStartNodeStateNum(var.getStartNode().getStateNum());
        NfaManager.traverse(var.getStartNode(),  new NodeHandle<NfaStateNode>() {
            public void handle(NfaStateNode o, int i)throws Exception {
                handleMapSec(o,context, i);
            }
        });
        return context;
    }

    private void handleMapSec(NfaStateNode o,DfaContext context,int i)throws Exception{
        //判断是开始节点
        if(o.getState().getState()==NfaState.START.getState()){
            context.setStartNodeStateNum(o.getStateNum());
        }
        //判断是结束节点
        if(o.getState().getState()==NfaState.END.getState()){
            context.setEndNodeStateNum(o.getStateNum());
        }
        //建立状态码与节点的对应关系
        context.putMapState(o.getStateNum(),o);
        //获取状态值
        String stateNum = o.getStateNum();
        Map<Integer, List<String>> integerListMap = context.getMap().get(stateNum);
        if(integerListMap==null){
            //没有的话，赋值
            Map<Integer,List<String>> map1=new HashMap<Integer, List<String>>();
            context.getMap().put(stateNum,map1);
        }

        //获取当前条线
        EdgeLine edgeLine = o.getEdgeLines()[i];
        EdgeInputType edgeInputType = edgeLine.getEdgeInputType();
        //如果当前条线的类型是空
        if(edgeInputType==EdgeInputType.NULL_GATHER){
            List<Character> edgeAllowInputGather = edgeLine.getEdgeAllowInputGather();
            if(edgeAllowInputGather!=null && edgeAllowInputGather.size()>0){
                ExceptionTools.ThrowException("空集里边不允许有值，请排查");
            }
            //记录列
            context.getInputParams().add(130);
            context.handleMapCol(integerListMap,130,edgeLine.getNext().getStateNum());
        }else {
            List<Character> edgeAllowInputGather = edgeLine.getEdgeAllowInputGather();
            if(edgeAllowInputGather==null || edgeAllowInputGather.size()==0){
                ExceptionTools.ThrowException("条线里边必须有值，请排查");
            }
            //单个字符处理
            if(edgeAllowInputGather.size()==1){
                context.getInputParams().add(Integer.valueOf(edgeAllowInputGather.get(0)));

                context.handleMapCol(integerListMap,
                        Integer.valueOf(edgeAllowInputGather.get(0))
                        ,edgeLine.getNext().getStateNum());
            }else{
                //字符集处理，化为单个列处理
                for(Character ch:edgeAllowInputGather){
                    context.getInputParams().add(Integer.valueOf(ch));
                    context.handleMapCol(integerListMap,Integer.valueOf(ch),edgeLine.getNext().getStateNum());
                }

            }
        }
    }
}
