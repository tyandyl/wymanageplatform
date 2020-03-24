package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/24.
 */
public class SpCharacterCarveCapacity implements CharacterCarveCapacity{
    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
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
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.SP.getState());
                    xContentItemL.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemL.addIndex(pop.getIndex());
                    stack.push(xContentItemL);
                }
            }else if(specialCurlyStart.size()>0){
                XContentItem peek = stack.peek();
                peek.addIndex(i);
            }else {
                xContentItemL.setMeanType(MeanType.NO_CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine=null;
                //这里只剩下(了，
                if(pop.getNfaStateMachine()==null){
                    stack.push(pop);
                    linkNfaStateMachine=NfaManager.createSingleCharacterNfaStateMachine(SymbolType.SP.getState());
                }else {
                    linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createSingleCharacterNfaStateMachine(SymbolType.SP.getState()));
                }
                xContentItemL.setNfaStateMachine( linkNfaStateMachine);
                xContentItemL.addIndex(pop.getIndex());
                stack.push(xContentItemL);
            }

        }
        return 0;
    }
}
