package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/17.
 */
public class AtEolCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemAtEol=new XContentItem(array[i],i);

        if(stack.empty()){
            throw new Exception("$不能放在首位");
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemAtEol.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    xContentItemAtEol.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemAtEol);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.AT_EOL.getState());
                    xContentItemAtEol.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemAtEol.addIndex(pop.getIndex());
                    stack.push(xContentItemAtEol);
                }
            }else {
                if(!(i==array.length-1)){
                    throw new Exception("$没有在末位");
                }
            }

        }
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            throw new Exception("$不应该在{}内");
        }
        return 0;
    }
}
