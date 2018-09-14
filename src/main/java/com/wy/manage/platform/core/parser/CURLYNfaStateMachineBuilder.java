package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/9.
 */
public class CURLYNfaStateMachineBuilder extends NfaStateMachineBuilder{

    @Override
    public void build(Stack<XContentItem> analyzeResult, Stack<XContentItem> stack,char[] array,int i) throws Exception {
        Integer pop = analyzeResult.pop().getLegend();
        Integer peek1 = null;
        if (!analyzeResult.empty()) {
            peek1 = analyzeResult.peek().getLegend();
        }

        if (peek1 != null
                && peek1 == SymbolType.COMMA.getState()
                && Character.isDigit(pop)) {
            int pre = ((char)pop.intValue())- '0';
            analyzeResult.pop();
            int later=9999;
            if(!analyzeResult.empty()){
                int legend = analyzeResult.pop().getLegend();
                later = ((char)legend)- '0';
            }

            //需要获取{}外边的值
            XContentItem peek = stack.peek();
            if(peek.getNfaStateMachine()==null){
                //==null时，默认都是非转义的
                XContentItem pop1 = stack.pop();
                List<Character> list=new ArrayList<Character>();
                list.add((char)(pop1.getLegend()));
                NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(list);
                NfaStateMachine repetitionAddNumNfaStateMachine = NfaManager.createRepetitionAddNumNfaStateMachine(characterRepertoireNfaStateMachine, pre, later);
                pop1.setNfaStateMachine(repetitionAddNumNfaStateMachine);
                stack.push(pop1);
            }else {
                XContentItem pop2 = stack.pop();
                NfaStateMachine repetitionAddNumNfaStateMachine = NfaManager.createRepetitionAddNumNfaStateMachine(pop2.getNfaStateMachine(), pre, later);
                pop2.setNfaStateMachine(repetitionAddNumNfaStateMachine);
                stack.push(pop2);
            }
        }
    }
}
