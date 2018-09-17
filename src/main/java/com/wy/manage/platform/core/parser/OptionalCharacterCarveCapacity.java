package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/14.
 */
public class OptionalCharacterCarveCapacity implements CharacterCarveCapacity{
    public void build(Stack<XContentItem> analyzeResult, Stack<XContentItem> stack,char[] array,int i) throws Exception {

    }

    public Stack<XContentItem> analyze(Stack<XContentItem> stack,char[] array,int i,SymbolType pre,SymbolType later)throws Exception{
        while (!stack.empty()){
            XContentItem popClosure = stack.pop();
            if(popClosure.getNfaStateMachine()==null){
                MeanType meanType = popClosure.getMeanType();
                if(meanType==MeanType.NO_CHANGE_MEANING){
                    NfaStateMachine singleCharacterNfaStateMachine = NfaManager.createSingleCharacterNfaStateMachine(popClosure.getLegend());
                    NfaStateMachine repetitionStarNfaStateMachine = NfaManager.createRepetitionQuestionMarkNfaStateMachine(singleCharacterNfaStateMachine);
                    popClosure.setNfaStateMachine(repetitionStarNfaStateMachine);
                    stack.push(popClosure);
                    break;
                }else {
                    //暂不处理，想想?前边跟正则的公式
                }
            }else{
                NfaStateMachine repetitionStarNfaStateMachine = NfaManager.createRepetitionQuestionMarkNfaStateMachine(popClosure.getNfaStateMachine());
                popClosure.setNfaStateMachine(repetitionStarNfaStateMachine);
                stack.push(popClosure);
                break;
            }
        }
        return null;
    }

    public void carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemAny=new XContentItem(array[i],i);
        if(stack.empty()){
            xContentItemAny.setNfaStateMachine( NfaManager.createAnyCharacterRepertoireNfaStateMachine());
            xContentItemAny.setMeanType(MeanType.CHANGE_MEANING);
            stack.push(xContentItemAny);
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemAny.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    xContentItemAny.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemAny);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.ANY.getState());
                    xContentItemAny.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemAny.addIndex(pop.getIndex());
                    stack.push(xContentItemAny);
                }
            }else {
                xContentItemAny.setMeanType(MeanType.CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createAnyCharacterRepertoireNfaStateMachine());
                xContentItemAny.setNfaStateMachine( linkNfaStateMachine);
                xContentItemAny.addIndex(pop.getIndex());
                stack.push(xContentItemAny);
            }

        }
    }
}
