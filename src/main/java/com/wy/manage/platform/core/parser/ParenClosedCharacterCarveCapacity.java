package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/9.
 */
public class ParenClosedCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialParenStart = context.getSpecialParenStart();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemParen=new XContentItem(array[i],i);
        if(specialParenStart.size()==0){
            throw new Exception("()没有匹配");
        }
        Stack<XContentItem> stack = context.getStack();
        if(stack.empty()){
            throw new Exception("()没有匹配");
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemParen.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    xContentItemParen.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemParen);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.CLOSE_PAREN.getState());
                    xContentItemParen.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemParen.addIndex(pop.getIndex());
                    stack.push(xContentItemParen);
                }
            }else{
                xContentItemParen.setMeanType(MeanType.CHANGE_MEANING);
                XContentItem pop = stack.pop();

            }
        }

        return 0;

    }
}
