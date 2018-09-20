package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class BackSlashCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            ExceptionTools.ThrowException("\\不应该在{}内");
        }
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemBackSlash=new XContentItem(array[i],i);
        if(stack.empty()){
            xContentItemBackSlash=new XContentItem(array[i+1],i+1,MeanType.NO_CHANGE_MEANING);
            NfaStateMachine singleCharacterNfaStateMachine = NfaManager.createSingleCharacterNfaStateMachine(array[i + 1]);
            xContentItemBackSlash.setNfaStateMachine(singleCharacterNfaStateMachine);
            stack.add(xContentItemBackSlash);
            return 1;
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemBackSlash.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    xContentItemBackSlash.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemBackSlash);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.BACKSLASH.getState());
                    xContentItemBackSlash.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemBackSlash.addIndex(pop.getIndex());
                    stack.push(xContentItemBackSlash);
                }
            }else {
                xContentItemBackSlash=new XContentItem(array[i+1],i+1,MeanType.NO_CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine=null;
                if(pop.getNfaStateMachine()==null){
                    stack.push(pop);
                    linkNfaStateMachine = NfaManager.createSingleCharacterNfaStateMachine(array[i + 1]);
                }else {
                    linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createSingleCharacterNfaStateMachine(array[i+1]));
                }
                xContentItemBackSlash.setNfaStateMachine( linkNfaStateMachine);
                xContentItemBackSlash.addIndex(pop.getIndex());
                stack.push(xContentItemBackSlash);
                return 1;
            }

        }

        return 0;
    }
}