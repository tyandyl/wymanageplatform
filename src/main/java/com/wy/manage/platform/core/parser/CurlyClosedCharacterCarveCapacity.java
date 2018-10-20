package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class CurlyClosedCharacterCarveCapacity implements CharacterCarveCapacity{


    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        Stack<XContentItem> stack = context.getStack();
        if(specialCurlyStart.size()==0){
            ExceptionTools.ThrowException("{}没有匹配");
        }
        if(stack.empty()){
            ExceptionTools.ThrowException("{}没有匹配");
        }
        XContentItem pop = stack.pop();
        XContentItem peek = stack.peek();
        if(pop.getMeanType()==MeanType.CHANGE_MEANING
                && pop.getLegend()==SymbolType.OPEN_CURLY.getState()){
            List<Integer> charCurly = context.getCharCurly();
            if(charCurly.size()==1){
                ExceptionTools.ThrowException("{}解析错误,目前不支持无限");
                NfaStateMachine nfaStateMachine = peek.getNfaStateMachine();
                NfaStateMachine repetitionAddNumNfaStateMachine = NfaManager.createRepetitionAddNumNfaStateMachine(nfaStateMachine, charCurly.get(0), 999);
                peek.setNfaStateMachine(repetitionAddNumNfaStateMachine);
                peek.addIndex(pop.getIndex());
                peek.addIndex(i);
            }else if(charCurly.size()==2){
                NfaStateMachine nfaStateMachine = peek.getNfaStateMachine();
                NfaStateMachine repetitionAddNumNfaStateMachine = NfaManager.createRepetitionAddNumNfaStateMachine(nfaStateMachine, charCurly.get(0), charCurly.get(1));
                peek.setNfaStateMachine(repetitionAddNumNfaStateMachine);
                peek.addIndex(pop.getIndex());
                peek.addIndex(i);
            } else {
                ExceptionTools.ThrowException("{}解析错误,{}内无值或者多值");
            }
            specialCurlyStart.remove(specialCurlyStart.size()-1);
        }else {
            ExceptionTools.ThrowException("{}解析错误");
        }
        return 0;

    }
}
