package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye13 on 2018/9/18.
 */
public class ParenOpenedCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCclStart = context.getSpecialCclStart();
        List<Integer> specialParenStart = context.getSpecialParenStart();
        Stack<XContentItem> stack = context.getStack();

        XContentItem xContentItemCclStarted=new XContentItem(array[i],i);
        if(stack.empty()){
            xContentItemCclStarted.setMeanType(MeanType.CHANGE_MEANING);
            stack.push(xContentItemCclStarted);
            specialParenStart.add(i);
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemCclStarted.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    xContentItemCclStarted.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemCclStarted);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.OPEN_PAREN.getState());
                    xContentItemCclStarted.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemCclStarted.addIndex(pop.getIndex());
                    stack.push(xContentItemCclStarted);
                }
            }else {
                xContentItemCclStarted.setMeanType(MeanType.CHANGE_MEANING);
                stack.push(xContentItemCclStarted);
                specialParenStart.add(i);
            }

        }

        return 0;
    }
}
