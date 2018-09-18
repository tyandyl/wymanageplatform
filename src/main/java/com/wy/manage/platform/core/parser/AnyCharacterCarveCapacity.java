package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/17.
 */
public class AnyCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            throw new Exception(".不应该在{}内");
        }
        //.不知道是连接还是或，在外边是连接在[.]是或的意思，
        //比如[\w./-+]
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemAny=new XContentItem(array[i],i);
        if(stack.empty()){
            xContentItemAny.setNfaStateMachine( NfaManager.createAnyCharacterRepertoireNfaStateMachine());
            xContentItemAny.setMeanType(MeanType.CHANGE_MEANING);
            stack.push(xContentItemAny);
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
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.ANY.getState());
                    xContentItemAny.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemAny.addIndex(pop.getIndex());
                    stack.push(xContentItemAny);
                }
            }else {
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine=null;
                if(pop.getNfaStateMachine()!=null){
                    xContentItemAny.setMeanType(MeanType.CHANGE_MEANING);
                    linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createAnyCharacterRepertoireNfaStateMachine());
                    xContentItemAny.addIndex(pop.getIndex());
                }else {
                    stack.push(pop);
                    //除了（[{第一个字母之外，都有状态机,这里排除了[和{，只剩下（了
                    linkNfaStateMachine = NfaManager.createAnyCharacterRepertoireNfaStateMachine();
                }
                xContentItemAny.setNfaStateMachine( linkNfaStateMachine);
                stack.push(xContentItemAny);

            }

        }

        return 0;


    }
}
