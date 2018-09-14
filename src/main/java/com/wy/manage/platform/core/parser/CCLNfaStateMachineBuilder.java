package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye13 on 2018/9/9.
 */
public class CCLNfaStateMachineBuilder extends NfaStateMachineBuilder{
    /**
     * 我们要解决[0-9],[a-z],[a-zA-Z],[A-Za-z0-9]
     * [\w./-+]是匹配\w [0-9a-zA-Z_] 或 . 或 / 或 - 或 + 字符；
     * 在[./-+]内均表示字符本身
     * 在[.]内点，不是任意字符的意思，就是匹配点.字符本身，点.可以不需要加反斜杠\.
     * 在[]内特殊字符，表示匹配特殊字符本身，不需要加反斜杠
     * 在[]外特殊字符，表示匹配特殊字符本身，必须要加反斜杠
     * @param analyzeResult
     * @param stack
     * @throws Exception
     */
    @Override
    public void build(Stack<XContentItem> analyzeResult, Stack<XContentItem> stack,char[] array,int i) throws Exception {
        XContentItem itemFirst = analyzeResult.peek();
        Integer peek = itemFirst.getLegend();
        boolean isNegation = false;
        if (peek == SymbolType.AT_BOL.getState()
                && itemFirst.getMeanType()==MeanType.CHANGE_MEANING) {
            isNegation = true;
            analyzeResult.pop();
        }
        List<Character> characterList = new ArrayList<Character>();
        while (!analyzeResult.empty()) {
            XContentItem pop = analyzeResult.pop();
            Integer popInt =pop.getLegend();
            Integer peek1 = null;
            if (!analyzeResult.empty()) {
                peek1 = analyzeResult.peek().getLegend();
            }

            if (peek1 != null
                    && peek1 == SymbolType.DASH.getState()
                    &&  analyzeResult.peek().getMeanType()==MeanType.CHANGE_MEANING
                    && Character.isLetterOrDigit(popInt)) {
                int pre = popInt;
                analyzeResult.pop();
                if (analyzeResult.empty()) {
                    throw new Exception("-后边必须跟着字符");
                }
                int later = analyzeResult.pop().getLegend();
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
            NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
            if(nfaStateMachine!=null){
                throw new Exception("[]目前内不支持生成自动机");
            }
            MeanType meanType = pop.getMeanType();
            if(meanType==MeanType.CHANGE_MEANING){
                throw new Exception("[]目前内不允许有转义");
            }
            if (!characterList.contains((char) (popInt.intValue()))) {
                characterList.add((char) (popInt.intValue()));
            }
        }
        //考虑取反
        if (isNegation) {

        }

        NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(characterList);
        XContentItem item=new XContentItem(characterRepertoireNfaStateMachine);
        stack.push(item);
    }
}
