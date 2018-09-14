package com.wy.manage.platform.core.parser;

import java.util.Stack;

/**
 * Created by tianye on 2018/9/14.
 */
public class CLOSURENfaStateMachineBuilder extends NfaStateMachineBuilder{
    @Override
    public void build(Stack<XContentItem> analyzeResult, Stack<XContentItem> stack,char[] array,int i) throws Exception {

    }
    public Stack<XContentItem> analyze(Stack<XContentItem> stack,char[] array,int i,SymbolType pre,SymbolType later)throws Exception{
        while (!stack.empty()){
            XContentItem popClosure = stack.pop();
            if(popClosure.getNfaStateMachine()==null){
                MeanType meanType = popClosure.getMeanType();
                if(meanType==MeanType.NO_CHANGE_MEANING){
                    NfaStateMachine singleCharacterNfaStateMachine = NfaManager.createSingleCharacterNfaStateMachine(popClosure.getLegend());
                    NfaStateMachine repetitionStarNfaStateMachine = NfaManager.createRepetitionStarNfaStateMachine(singleCharacterNfaStateMachine);
                    popClosure.setNfaStateMachine(repetitionStarNfaStateMachine);
                    stack.push(popClosure);
                    break;
                }else {
                    //暂不处理，想想*前边跟正则的公式
                }
            }else{
                NfaStateMachine repetitionStarNfaStateMachine = NfaManager.createRepetitionStarNfaStateMachine(popClosure.getNfaStateMachine());
                popClosure.setNfaStateMachine(repetitionStarNfaStateMachine);
                stack.push(popClosure);
                break;
            }
        }
        return null;
    }

}
