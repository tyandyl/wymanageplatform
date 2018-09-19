package com.wy.manage.platform.core.parser.test;

import com.wy.manage.platform.core.parser.*;

import java.util.List;

/**
 * 测试|
 */
public class Test3 {
    public static void main(String[] agrs) throws Exception {
        String m="a|m";
        NfaStateMachine nfaStateMachine = RegularExpressionParser.parserCss(m.toCharArray()).peek().getNfaStateMachine();
        String s = "m";
        char[] chars = s.toCharArray();
        NfaStateNode startNode = nfaStateMachine.getStartNode();
        for(int i=0;i<chars.length;i++){
            NfaStateNode analyze = analyze(startNode, chars[i],0);
            if(analyze==null){
                System.out.println("结束");
            }else {
                startNode=analyze;
            }
        }
        System.out.println(startNode);
    }

    public static NfaStateNode analyze(NfaStateNode nfaStateNode, char sy, int ny){
        EdgeLine[] edgeLines = nfaStateNode.getEdgeLines();
        for(int i=0;i<edgeLines.length;i++){
            if(edgeLines[i]!=null){
                EdgeInputType edgeInputType = edgeLines[i].getEdgeInputType();
                if(edgeInputType==EdgeInputType.NULL_GATHER ){
                    NfaStateNode next = edgeLines[i].getNext();
                    if(next!=null){
                        if(next.getState()==NfaState.END){
                            System.out.println("遇到末级节点");
                            return nfaStateNode;
                        }
                        NfaStateNode analyze = analyze(next, sy, ny);
                        if(analyze==null){
                            continue;
                        }
                        return analyze;
                    }else {
                        System.out.println("下一个节点为空");
                    }
                }else if(edgeInputType==EdgeInputType.CHARACTER_REPERTOIRE){
                    if(ny==9){
                        return nfaStateNode;
                    }
                    List<Character> edgeAllowInputGather = edgeLines[i].getEdgeAllowInputGather();
                    if(edgeAllowInputGather.contains(sy)){

                        NfaStateNode next = edgeLines[i].getNext();
                        System.out.println("成功识别--------开始找头");
                        return analyze(next,sy,9);
                    }else {
                        return null;
                    }
                }
            }else {
                System.out.println("edgeLines["+i+"]为空");
            }
        }
        return null;

    }
}
