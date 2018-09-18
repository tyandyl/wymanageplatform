package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/18.
 */
public class LCharacterCarveCapacity implements CharacterCarveCapacity{
    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemL=new XContentItem(array[i],i);
        if(stack.empty()){
            xContentItemL.setNfaStateMachine( NfaManager.createSingleCharacterNfaStateMachine(array[i]));
            xContentItemL.setMeanType(MeanType.NO_CHANGE_MEANING);
            stack.push(xContentItemL);
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemL.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    xContentItemL.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemL);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,array[i]);
                    xContentItemL.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemL.addIndex(pop.getIndex());
                    stack.push(xContentItemL);
                }
            }else {
                xContentItemL.setMeanType(MeanType.NO_CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine=null;
                //除了（[{第一个字母之外，都有状态机
                if(pop.getNfaStateMachine()==null){
                    linkNfaStateMachine=NfaManager.createSingleCharacterNfaStateMachine(array[i]);
                }else {
                    linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createAnyCharacterRepertoireNfaStateMachine());
                }
                xContentItemL.setNfaStateMachine( linkNfaStateMachine);
                xContentItemL.addIndex(pop.getIndex());
                stack.push(xContentItemL);
            }

        }
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            throw new Exception(".不应该在{}内");
        }
        return 0;
    }
}
