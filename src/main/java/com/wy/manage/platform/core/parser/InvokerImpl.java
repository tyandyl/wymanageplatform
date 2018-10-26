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


}
