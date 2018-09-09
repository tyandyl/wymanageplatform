package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye13 on 2018/9/9.
 */
public class CCLNfaStateMachineBuilder extends NfaStateMachineBuilder{
    @Override
    public List<Character> getCharacterRepertoire(Stack<Integer> stack) throws Exception {
        Integer peek = stack.peek();
        boolean isNegation = false;
        if (peek == SymbolType.AT_BOL.getState()) {
            isNegation = true;
            stack.pop();
        }
        List<Character> characterList = new ArrayList<Character>();
        while (!stack.empty()) {
            Integer pop = stack.pop();
            Integer peek1 = null;
            if (!stack.empty()) {
                peek1 = stack.peek();
            }
            if (pop == SymbolType.AT_BOL.getState()) {
                throw new Exception("[]只能有一个^");
            }
            if (peek1 != null && peek1 == SymbolType.DASH.getState() && Character.isLetter(pop)) {
                int pre = pop;
                stack.pop();
                if (stack.empty()) {
                    throw new Exception("-后边必须跟着字符");
                }
                int later = stack.pop();
                if (pre > later) {
                    throw new Exception("-后边必须大于前边");
                }
                while (pre <= later) {
                    if (!characterList.contains((char) pre)) {
                        characterList.add((char) pre);
                    }
                    pre++;
                }
            }
            //这里我们只是简单解析，不考虑[]中有\b等转义字符
            if (pop == SymbolType.BACKSLASH.getState()) {
                if (peek1 == null) {
                    throw new Exception("\\后边必须跟着字符");
                }
                pop = stack.pop();
            }
            if (!characterList.contains((char) (pop.intValue()))) {
                characterList.add((char) (pop.intValue()));
            }
        }
        //暂不考虑取反
        if (isNegation) {

        }
        return characterList;
    }

    @Override
    public NfaStateMachine createNfaStateMachine(List<Character> list) throws Exception {
        return NfaManager.createCharacterRepertoireNfaStateMachine(list);
    }
}
