package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/9.
 */
public abstract class NfaStateMachineBuilder {
    public abstract List<Character> getCharacterRepertoire(Stack<Integer> stack)throws Exception;

    public abstract NfaStateMachine createNfaStateMachine(List<Character> list)throws Exception;

    public NfaStateMachine builder(Stack<XContentItem> stack,String pre,String later)throws Exception{
        Stack<Integer> analyzeResult = analyze(stack,pre,later);
        List<Character> characterRepertoire = getCharacterRepertoire(analyzeResult);
        return createNfaStateMachine(characterRepertoire);
    }

    public Stack<Integer> analyze(Stack<XContentItem> stack,String pre,String later)throws Exception{
        boolean isEmpty = stack.empty();
        if(isEmpty){
            throw new Exception(pre+"必须和"+later+"成对");
        }
        Stack<Integer> stack1=new Stack<Integer>();
        boolean isMatching=false;
        while (!stack.empty()){
            XContentItem pop1 = stack.pop();
            int legend = pop1.getLegend();
            SymbolType symbolType1 = SymbolType.findSymbolType(legend);
            if(symbolType1==SymbolType.CCL_START){
                isMatching=true;
                break;
            }else {
                stack1.push(legend);
            }
        }
        if(!isMatching){
            throw new Exception(pre+"必须和"+later+"成对");
        }
        boolean empty = stack1.empty();
        if(empty){
            throw new Exception(pre+"和"+later+"之间必须有字符");
        }
        return stack1;
    }

}
