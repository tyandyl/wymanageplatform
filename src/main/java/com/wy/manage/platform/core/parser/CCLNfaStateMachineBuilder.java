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
     * @return
     * @throws Exception
     */
    @Override
    public List<Character> getCharacterRepertoire(Stack<XContentItem> analyzeResult) throws Exception {
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
            Integer pop = analyzeResult.pop().getLegend();
            Integer peek1 = null;
            if (!analyzeResult.empty()) {
                peek1 = analyzeResult.peek().getLegend();
            }

            if (peek1 != null
                    && peek1 == SymbolType.DASH.getState()
                    &&  analyzeResult.peek().getMeanType()==MeanType.CHANGE_MEANING
                    && Character.isLetterOrDigit(pop)) {
                int pre = pop;
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
            if (!characterList.contains((char) (pop.intValue()))) {
                characterList.add((char) (pop.intValue()));
            }
        }
        //考虑取反
        if (isNegation) {

        }
        return characterList;
    }

    @Override
    public NfaStateMachine createNfaStateMachine(List<Character> list,int least,int max) throws Exception {
        return NfaManager.createCharacterRepertoireNfaStateMachine(list);
    }
}
