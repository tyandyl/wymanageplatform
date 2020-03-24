package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye13 on 2018/9/21.
 */
public class PlusCharacterCarveCapacity implements CharacterCarveCapacity{
    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            ExceptionTools.ThrowException("+不应该在{}内");
        }
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemClosure=new XContentItem(array[i],i);
        if(stack.empty()){
            ExceptionTools.ThrowException("+不能放在首位");
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemClosure.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    xContentItemClosure.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemClosure);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.PLUS.getState());
                    xContentItemClosure.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemClosure.addIndex(pop.getIndex());
                    stack.push(xContentItemClosure);
                }
            }else {
                xContentItemClosure.setMeanType(MeanType.CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine=null;
                if(pop.getNfaStateMachine()==null){
                    ExceptionTools.ThrowException("+不能放在(第一位");
                }else {
                    linkNfaStateMachine = NfaManager.createRepetitionAddNfaStateMachine(pop.getNfaStateMachine());
                }
                xContentItemClosure.setNfaStateMachine( linkNfaStateMachine);
                xContentItemClosure.addIndex(pop.getIndex());
                stack.push(xContentItemClosure);
            }

        }
        return 0;
    }
}
