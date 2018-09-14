package com.wy.manage.platform.core.parser;

import java.util.Stack;

/**
 * Created by tianye on 2018/9/14.
 */
public class ORNfaStateMachineBuilder extends NfaStateMachineBuilder{
    @Override
    public void build(Stack<XContentItem> analyzeResult, Stack<XContentItem> stack,char[] array,int i) throws Exception {

    }

    public Stack<XContentItem> analyze(Stack<XContentItem> stack,char[] array,int i,SymbolType pre,SymbolType later)throws Exception{
        XContentItem xContentItemOr=new XContentItem(array[i],i);
        Stack<Character> stackChars=new Stack<Character>();
        while (!stack.empty()){
            XContentItem popClosure = stack.pop();
            if(popClosure.getNfaStateMachine()==null){
                MeanType meanType = popClosure.getMeanType();
                if(meanType==MeanType.NO_CHANGE_MEANING){
                    stackChars.push((char)(popClosure.getLegend()));
                }else {
                    //暂不处理，想想|前边跟正则的公式
                    throw new Exception("|前边跟正则的公式");
                }
            }else{
                if(stackChars.empty()){
                    xContentItemOr.setNfaStateMachine(popClosure.getNfaStateMachine());
                    xContentItemOr.setOr(true);
                    stack.push(xContentItemOr);
                    break;
                }else {
                    stack.push(popClosure);
                    NfaStateMachine nfaStateMachine=null;
                    while (!stackChars.empty()){
                        Character pop = stackChars.pop();
                        NfaStateMachine singleCharacterNfaStateMachine = NfaManager.createSingleCharacterNfaStateMachine(pop);
                        if(nfaStateMachine==null){
                            nfaStateMachine=singleCharacterNfaStateMachine;
                        }else {
                            nfaStateMachine = NfaManager.createLinkNfaStateMachine(nfaStateMachine, singleCharacterNfaStateMachine);
                        }
                    }
                    xContentItemOr.setNfaStateMachine(nfaStateMachine);
                    xContentItemOr.setOr(true);
                    stack.push(xContentItemOr);
                    break;
                }

            }
        }
        return null;
    }
}
