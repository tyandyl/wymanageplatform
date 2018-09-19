package com.wy.manage.platform.core.parser.test;

import com.wy.manage.platform.core.parser.*;

import java.util.List;

/**
 * 测试6{2,5}
 * Created by tianye
 */
public class Test2 {
    public static void main(String[] agrs) throws Exception {

        String m="6{0,2}";
        NfaStateMachine nfaStateMachine = RegularExpressionParser.parserCss(m.toCharArray()).peek().getNfaStateMachine();
        String s = "666";
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

    public static NfaStateNode analyze(NfaStateNode nfaStateNode,char sy,int ny){
        EdgeLine[] edgeLines = nfaStateNode.getEdgeLines();
        for(int i=0;i<edgeLines.length;i++){
            if(edgeLines[i]!=null){
                EdgeInputType edgeInputType = edgeLines[i].getEdgeInputType();
                if(edgeInputType==EdgeInputType.NULL_GATHER ){
                    int least = edgeLines[i].getLeast();
                    if(least>-1){
                        int useNum = edgeLines[i].getUseNum();
                        if(useNum>=edgeLines[i].getMax()){
                            System.out.println("超了");
                            return null;
                        }
                        edgeLines[i].setUseNum((useNum+1));
                    }
                    NfaStateNode next = edgeLines[i].getNext();
                    if(next!=null){
                        System.out.println("当前连接线是空");
                        if(next.getState()==NfaState.END){
                            return nfaStateNode;
                        }
                        return analyze(next,sy,ny);
                    }else {
                        System.out.println("下一个节点为空");
                    }
                }else if(edgeInputType==EdgeInputType.CHARACTER_REPERTOIRE){
                    if(ny==9){
                        return nfaStateNode;
                    }
                    System.out.println("当前连接线是字符集");
                    List<Character> edgeAllowInputGather = edgeLines[i].getEdgeAllowInputGather();
                    if(edgeAllowInputGather.contains(sy)){

                        NfaStateNode next = edgeLines[i].getNext();
                        System.out.println("成功识别--------开始找头");
                        return analyze(next,sy,9);
                    }
                }
            }else {
                System.out.println("edgeLines["+i+"]为空");
            }
        }
        return null;

    }

}


