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
        int u=0;
        if(array[i+1]=='s'){
            u=SymbolType.SP.getState();
        }else {
            u=array[i+1];
        }
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemBackSlash=new XContentItem(array[i],i);
        if(stack.empty()){
            xContentItemBackSlash=new XContentItem(u,i+1,MeanType.NO_CHANGE_MEANING);
            NfaStateMachine singleCharacterNfaStateMachine = NfaManager.createSingleCharacterNfaStateMachine(array[i + 1]);
            xContentItemBackSlash.setNfaStateMachine(singleCharacterNfaStateMachine);
            xContentItemBackSlash.addIndex(i);
            xContentItemBackSlash.addIndex(i+1);
            stack.add(xContentItemBackSlash);
            return 1;
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemBackSlash.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    if(array[i+1]=='s'){
                        xContentItemBackSlash.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(SymbolType.SP.getState()));
                        xContentItemBackSlash.addIndex(i);
                        xContentItemBackSlash.addIndex(i+1);
                        stack.push(xContentItemBackSlash);
                        return 1;
                    }else {
                        xContentItemBackSlash.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    }
                    stack.push(xContentItemBackSlash);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine=null;
                    if(array[i+1]=='s'){
                        characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.SP.getState());
                        xContentItemBackSlash.addIndex(i);
                        xContentItemBackSlash.addIndex(i+1);
                        xContentItemBackSlash.setNfaStateMachine(characterRepertoireNfaStateMachine);
                        xContentItemBackSlash.addIndex(pop.getIndex());
                        stack.push(xContentItemBackSlash);
                        return 1;
                    }
                    characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.BACKSLASH.getState());
                    xContentItemBackSlash.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemBackSlash.addIndex(pop.getIndex());
                    stack.push(xContentItemBackSlash);
                }
            }else {
                xContentItemBackSlash=new XContentItem(u,i+1,MeanType.NO_CHANGE_MEANING);
                xContentItemBackSlash.addIndex(i);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine=null;

                if(pop.getNfaStateMachine()==null){
                    stack.push(pop);
                    linkNfaStateMachine = NfaManager.createSingleCharacterNfaStateMachine(u);
                }else {
                    linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createSingleCharacterNfaStateMachine(u));
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
