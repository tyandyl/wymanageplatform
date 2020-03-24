package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class OptionalCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemAny=new XContentItem(array[i],i);
        if(stack.empty()){
            throw new Exception("?不能排在首位");
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
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.OPTIONAL.getState());
                    xContentItemAny.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemAny.addIndex(pop.getIndex());
                    stack.push(xContentItemAny);
                }
            }else {
                xContentItemAny.setMeanType(MeanType.CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine = NfaManager.createRepetitionQuestionMarkNfaStateMachine(pop.getNfaStateMachine());
                xContentItemAny.setNfaStateMachine( linkNfaStateMachine);
                xContentItemAny.addIndex(pop.getIndex());
                stack.push(xContentItemAny);
            }

        }
        return 0;
    }
}
