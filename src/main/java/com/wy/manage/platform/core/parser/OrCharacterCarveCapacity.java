package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/14.
 */
public class OrCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemClosure=new XContentItem(array[i],i);
        if(stack.empty()){
            throw new Exception("|不能放在首位");
        }else {
            if (specialCclStart.size() > 0) {
                XContentItem peek = stack.peek();
                xContentItemClosure.setMeanType(MeanType.NO_CHANGE_MEANING);
                if (peek.getMeanType() == MeanType.CHANGE_MEANING
                        && stack.peek().getLegend() == SymbolType.CCL_START.getState()) {
                    xContentItemClosure.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemClosure);
                } else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine, SymbolType.OR.getState());
                    xContentItemClosure.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemClosure.addIndex(pop.getIndex());
                    stack.push(xContentItemClosure);
                }
            } else {
                xContentItemClosure.setMeanType(MeanType.CHANGE_MEANING);
                XContentItem pop = stack.pop();
                xContentItemClosure.setNfaStateMachine(pop.getNfaStateMachine());
                xContentItemClosure.addIndex(pop.getIndex());
                xContentItemClosure.setOr(true);
                stack.push(xContentItemClosure);
            }
        }
        return 0;
    }
}
