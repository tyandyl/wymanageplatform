package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/9.
 */
public abstract class NfaStateMachineBuilder {
    public abstract List<Character> getCharacterRepertoire(Stack<Integer> stack,Stack<XContentItem> stackXContentItem)throws Exception;

    public abstract NfaStateMachine createNfaStateMachine(List<Character> list,int least,int max)throws Exception;

    public NfaStateMachine builder(Stack<XContentItem> stack,SymbolType pre,SymbolType later,int i)throws Exception{
        Stack<Integer> analyzeResult = analyze(stack,pre,later,i);
        if(analyzeResult==null){
            return null;
        }
        List<Character> characterRepertoire = getCharacterRepertoire(analyzeResult,stack);
        return createNfaStateMachine(characterRepertoire,0,0);
    }

    public Stack<Integer> analyze(Stack<XContentItem> stack,SymbolType pre,SymbolType later,int i)throws Exception{
        boolean isEmpty = stack.empty();
        if(isEmpty){
            throw new Exception(pre.getName()+"必须和"+later.getName()+"成对");
        }
        XContentItem peek = stack.peek();
        int legend1 = peek.getLegend();
        //说明上一个字符是\
        if(legend1==SymbolType.BACKSLASH.getState()){
            stack.pop();
            XContentItem xContentItem=new XContentItem(pre.getState(),i);
            stack.push(xContentItem);
            return null;
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
            throw new Exception(pre.getName()+"必须和"+later.getName()+"成对");
        }
        boolean empty = stack1.empty();
        if(empty){
            throw new Exception(pre.getName()+"和"+later.getName()+"之间必须有字符");
        }
        return stack1;
    }

}
