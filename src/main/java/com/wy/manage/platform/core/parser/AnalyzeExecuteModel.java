package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class AnalyzeExecuteModel {

    public static void execute(String str,NfaStateMachine nfaStateMachine) throws Exception {
        char[] chars = str.toCharArray();
        ModelParam<CssBag> modelParam=new ModelParam<CssBag>(nfaStateMachine.getStartNode(),chars,new CssBag());
        while (modelParam.getCurInt()<chars.length){
            if(modelParam.getNum()>=10000){
                System.out.println("执行失败");
                break;
            }
            analyzeEx( modelParam);
        }
        System.out.println(modelParam.getT());
    }

    public static void analyzeEx(ModelParam modelParam)throws Exception{
        EdgeLine[] edgeLines = modelParam.getStartNode().getEdgeLines();
        for(int i=0;i<2;i++){
            if(edgeLines[i]!=null && edgeLines[i].getEdgeType()!=EdgeType.PASSED){
                EdgeInputType edgeInputType = edgeLines[i].getEdgeInputType();
                if(edgeInputType==EdgeInputType.NULL_GATHER ){
                    if(edgeLines[i].getEdgeType()!=EdgeType.MAYBE){
                        edgeLines[i].setEdgeType(EdgeType.PASSED);
                    }else {
                        System.out.println("当前路径是maybe");
                    }
                    //记录当前节点和经过的字符
                    NfaStateNodeRecord nfaStateNodeRecord=new NfaStateNodeRecord(modelParam.getStartNode(),-1);
                    modelParam.getStackNodes().push(nfaStateNodeRecord);

                    NfaStateNode next = edgeLines[i].getNext();

                    if(next!=null){
                        modelParam.setStartNode(next);
                        if(next.getHandle()!=null){
                            next.getHandle().handle(modelParam,modelParam.getT());
                            modelParam.getCurModelValue().clear();
                        }
                        return;
                    }else {
                        ExceptionTools.ThrowException("没有与之对应的节点匹配");
                    }
                }else if(edgeInputType==EdgeInputType.CHARACTER_REPERTOIRE){
                    List<Character> edgeAllowInputGather = edgeLines[i].getEdgeAllowInputGather();
                    char[] chars = modelParam.getChars();
                    int curInt = modelParam.getCurInt();
                    if(edgeAllowInputGather.contains(chars[curInt])){
                        NfaStateNode next = edgeLines[i].getNext();
                        EdgeType edgeType = edgeLines[i].getEdgeType();
                        if(edgeType!=EdgeType.MAYBE){
                            edgeLines[i].setEdgeType(EdgeType.PASSED);
                        }
                        NfaStateNodeRecord nfaStateNodeRecord=new NfaStateNodeRecord(modelParam.getStartNode(),chars[curInt]);

                        modelParam.getStackNodes().push(nfaStateNodeRecord);
                        modelParam.setStartNode(next);
                        modelParam.setCurInt(curInt+1);
                        modelParam.getCurModelValue().add(chars[curInt]);
                        System.out.println("已匹配,当前的字符是:"+chars[curInt]);
                        if(next.getHandle()!=null){
                            next.getHandle().handle(modelParam,modelParam.getT());
                            modelParam.getCurModelValue().clear();
                        }
                        return;
                    }else {
                    }
                }
            }
        }

        modelParam.analyzeIsBack();
        return;
    }
}
