package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class CommaCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        XContentItem xContentItemComma=new XContentItem(array[i],i);
        if(stack.empty()){
            xContentItemComma.setNfaStateMachine( NfaManager.createSingleCharacterNfaStateMachine(array[i]));
            xContentItemComma.setMeanType(MeanType.CHANGE_MEANING);
            stack.push(xContentItemComma);
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemComma.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    xContentItemComma.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemComma);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.COMMA.getState());
                    xContentItemComma.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemComma.addIndex(pop.getIndex());
                    stack.push(xContentItemComma);
                }
            }else if(specialCurlyStart.size()>0){
                XContentItem peek = stack.peek();
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.OPEN_CURLY.getState()){
                    peek.addIndex(i);
                }else {
                    ExceptionTools.ThrowException("应该是{");
                }
            }else {
                xContentItemComma.setMeanType(MeanType.CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine=null;
                //只剩下（了
                if(pop.getNfaStateMachine()==null){
                    stack.push(pop);
                    linkNfaStateMachine=NfaManager.createSingleCharacterNfaStateMachine(array[i]);
                }else {
                    linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                }
                xContentItemComma.addIndex(pop.getIndex());

                xContentItemComma.setNfaStateMachine( linkNfaStateMachine);
                stack.push(xContentItemComma);

            }

        }

        return 0;
    }
}
