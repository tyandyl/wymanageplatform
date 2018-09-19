package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class AnalyzeExecuteModel {

    public static void execute(String str) throws Exception {
        String m="abc|fng";
        Stack<XContentItem> stack = RegularExpressionParser.parserCss(m.toCharArray());
        System.out.println("栈的长度是:"+stack.size());
        System.out.println("栈末级项的内容是:"+stack.peek().getIndex());
        if(stack.size()!=1){
            System.out.println("解析失败,请查找原因");
            ExceptionTools.ThrowException("解析失败,请查找原因");
        }
        char[] chars = str.toCharArray();
        NfaStateNode startNode = stack.peek().getNfaStateMachine().getStartNode();
        Stack<NfaStateNodeRecord> stackNodes=new Stack<NfaStateNodeRecord>();
        NfaStateNode analyze=null;
        for(int i=0;i<chars.length;i++){
            analyze = analyze(stackNodes,startNode, chars[i]);
            if(analyze==null){
                System.out.println("没有相对于的节点匹配");
                break;
            }else {
                startNode=analyze;
            }
        }
        if(analyze!=null){
            System.out.println("搞定");
        }


    }

    public static NfaStateNode analyze(Stack<NfaStateNodeRecord> stackNodes,NfaStateNode nfaStateNode, char sy)throws Exception {
        EdgeLine[] edgeLines = nfaStateNode.getEdgeLines();
        for(int i=0;i<edgeLines.length;i++){
            if(edgeLines[i]!=null && edgeLines[i].getEdgeType()!=EdgeType.PASSED){
                EdgeInputType edgeInputType = edgeLines[i].getEdgeInputType();
                if(edgeInputType==EdgeInputType.NULL_GATHER ){
                    if(edgeLines[i].getEdgeType()!=EdgeType.MAYBE){
                        edgeLines[i].setEdgeType(EdgeType.PASSED);
                    }
                    NfaStateNodeRecord nfaStateNodeRecord=new NfaStateNodeRecord(nfaStateNode,sy);
                    stackNodes.push(nfaStateNodeRecord);

                    NfaStateNode next = edgeLines[i].getNext();
                    if(next!=null){
                        return analyze(stackNodes,next, sy);
                    }else {
                        ExceptionTools.ThrowException("没有与之对应的节点匹配");
                    }
                }else if(edgeInputType==EdgeInputType.CHARACTER_REPERTOIRE){
                    List<Character> edgeAllowInputGather = edgeLines[i].getEdgeAllowInputGather();
                    if(edgeAllowInputGather.contains(sy)){
                        NfaStateNode next = edgeLines[i].getNext();
                        EdgeType edgeType = edgeLines[i].getEdgeType();
                        if(edgeType!=EdgeType.MAYBE){
                            edgeLines[i].setEdgeType(EdgeType.PASSED);
                        }
                        NfaStateNodeRecord nfaStateNodeRecord=new NfaStateNodeRecord(nfaStateNode,sy);
                        stackNodes.push(nfaStateNodeRecord);
                        System.out.println("成功识别"+sy);
                        return next;
                    }else {
                        NfaStateNodeRecord pop = stackNodes.pop();
                        return analyze(stackNodes,pop.getNfaStateNode(), (char)pop.getcRecord());
                    }
                }
            }
        }
        //null为报错不匹配，匹配总会返回一个状态机
        return null;

    }

}
