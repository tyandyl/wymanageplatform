package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/9.
 */
public class PARENNfaStateMachineBuilder extends NfaStateMachineBuilder{
    @Override
    public void build(Stack<XContentItem> analyzeResult, Stack<XContentItem> stack,char[] array,int i) throws Exception {
        XContentItem itemFirst = analyzeResult.peek();
        Integer peek = itemFirst.getLegend();

        List<Character> characterList = new ArrayList<Character>();
        NfaStateMachine nfaStateMachine=null;
        while (!analyzeResult.empty()) {
            XContentItem pop = analyzeResult.pop();
            NfaStateMachine nfaStateMachineItem=null;
            if(pop.getNfaStateMachine()==null){
                nfaStateMachineItem = NfaManager.createSingleCharacterNfaStateMachine(pop.getLegend());
            }else {
                nfaStateMachineItem=pop.getNfaStateMachine();
            }
            if(nfaStateMachine==null){
                nfaStateMachine=nfaStateMachineItem;
            }else {
                nfaStateMachine = NfaManager.createLinkNfaStateMachine(nfaStateMachine, nfaStateMachineItem);
            }
        }
        XContentItem item=new XContentItem(nfaStateMachine);
        stack.push(item);
    }
}
