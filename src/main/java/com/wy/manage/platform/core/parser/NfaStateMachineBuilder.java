package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/9.
 */
public abstract class NfaStateMachineBuilder {
    public abstract void build(Stack<XContentItem> analyzeResult,Stack<XContentItem> stack,char[] array,int i)throws Exception;

    public void builder(Stack<XContentItem> stack,char[] array,int i,SymbolType pre,SymbolType later)throws Exception{
        Stack<XContentItem> analyzeResult = analyze(stack,array,i,pre,later);
        if(analyzeResult==null){
            return;
        }
        build(analyzeResult,stack,array,i);
        return;
    }

    public Stack<XContentItem> analyze(Stack<XContentItem> stack,char[] array,int i,SymbolType pre,SymbolType later)throws Exception{
        Stack<XContentItem> stack1=new Stack<XContentItem>();
        boolean isMatching=false;
        while (!stack.empty()){
            XContentItem pop = stack.pop();
            //表明没有状态机
            if(pop.getNfaStateMachine()==null){
                int legend = pop.getLegend();
                SymbolType symbolType1 = SymbolType.findSymbolType(legend);
                if((symbolType1==SymbolType.CCL_START
                        || symbolType1==SymbolType.OPEN_CURLY
                        || symbolType1==SymbolType.OPEN_PAREN)
                        && pop.getMeanType()==MeanType.CHANGE_MEANING){
                    isMatching=true;
                    break;
                }else {
                    stack1.push(pop);
                }
            }else {
                stack1.push(pop);
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
