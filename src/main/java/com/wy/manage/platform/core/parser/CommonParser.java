package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.widget.SelectorType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye on 2018/9/24.
 */
public class CommonParser {

    public static NfaStateMachine parser()throws Exception{
        List<Character> list=new ArrayList<Character>();
        list.add((char)10);
        list.add((char)13);
        list.add((char)32);
        NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(list);
        NfaStateMachine repetitionStarNfaStateMachine = NfaManager.createRepetitionStarNfaStateMachine(characterRepertoireNfaStateMachine);
        NfaStateMachine nfaStateMachine = NfaManager.packNewStartAndEndNode(repetitionStarNfaStateMachine);
        return nfaStateMachine;
    }
}
