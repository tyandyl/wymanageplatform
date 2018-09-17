package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/9.
 */
public class CurlyCharacterCarveCapacity implements CharacterCarveCapacity{

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

    public void carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        Stack<XContentItem> stack = context.getStack();
        if(specialCurlyStart.size()==0){
            throw new Exception("{}没有匹配");
        }
        if(stack.empty()){
            throw new Exception("{}没有匹配");
        }
        XContentItem pop = stack.pop();
        XContentItem peek = stack.peek();
        if(peek.getMeanType()==MeanType.CHANGE_MEANING
                && stack.peek().getLegend()==SymbolType.OPEN_CURLY.getState()){
            XContentItem pop1 = stack.pop();
            pop.addIndex(pop1.getIndex());
            stack.push(pop);
            specialCurlyStart.remove(specialCurlyStart.size()-1);
        }else {
            throw new Exception("{}解析错误");
        }

    }
}
