package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class OrCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            ExceptionTools.ThrowException("|不应该在{}内");
        }
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemClosure=new XContentItem(array[i],i);
        if(stack.empty()){
            ExceptionTools.ThrowException("|不能放在首位");
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
                int length = array.length;
                int i1 = length - i - 1;
                if(i1==0){
                    ExceptionTools.ThrowException("截取到0了");
                }
                XContentItem pop = stack.pop();
                char[] arrayCp=new char[i1];
                System.arraycopy(array,(i+1),arrayCp,0,i1);
                XContentItem peek = RegularExpressionParser.parserCss(arrayCp).peek();
                NfaStateMachine nfaStateMachine =peek.getNfaStateMachine();
                xContentItemClosure.addIndex(peek.getIndex());
                xContentItemClosure.setMeanType(MeanType.CHANGE_MEANING);
                NfaStateMachine orNfaStateMachine = NfaManager.createOrNfaStateMachine(pop.getNfaStateMachine(), nfaStateMachine);
                xContentItemClosure.setNfaStateMachine(orNfaStateMachine);
                xContentItemClosure.addIndex(pop.getIndex());
                stack.push(xContentItemClosure);
            }
        }
        return 0;
    }
}
