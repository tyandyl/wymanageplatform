package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/9.
 */
public abstract class NfaStateMachineBuilder {
    public abstract List<Character> getCharacterRepertoire(Stack<Integer> stack)throws Exception;

    public abstract NfaStateMachine createNfaStateMachine(List<Character> list)throws Exception;

    public NfaStateMachine builder(Stack<Integer> stack)throws Exception{
        List<Character> characterRepertoire = getCharacterRepertoire(stack);
        return createNfaStateMachine(characterRepertoire);
    }
}
