package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/8.
 */
public class RegularExpressionParser {

    public static NfaStateMachine parserCss(String str) throws Exception {
        char[] array=str.toCharArray();
        Stack<XContentItem> stack=new Stack<XContentItem>();
        for(int i=0;i<array.length;i++){
            SymbolType symbolType = SymbolType.findSymbolType(array[i]);
            switch (symbolType){
                case CCL_END:
                    NfaStateMachineBuilder cclNfaStateMachineBuilder = NfaStateMachineFactory.getCCLNfaStateMachineBuilder();
                    NfaStateMachine cclBuilder = cclNfaStateMachineBuilder.builder(stack, SymbolType.CCL_END, SymbolType.CCL_START,i);
                    if(cclBuilder==null){
                        break;
                    }
                    XContentItem xContentItemCcl = new XContentItem(cclBuilder);
                    stack.add(xContentItemCcl);
                    break;
                case CLOSE_CURLY:
                    NfaStateMachineBuilder curlyNfaStateMachineBuilder = NfaStateMachineFactory.getCURLYNfaStateMachineBuilder();
                    NfaStateMachine curlyBuilder = curlyNfaStateMachineBuilder.builder(stack, SymbolType.OPEN_CURLY, SymbolType.CLOSE_CURLY,i);
                    if(curlyBuilder==null){
                        break;
                    }
                    XContentItem xContentItemCurly = new XContentItem(curlyBuilder);
                    stack.add(xContentItemCurly);
                    break;
                default:
                    XContentItem xContentItem=new XContentItem(array[i],i);
                    stack.add(xContentItem);
                    break;
            }

        }
        NfaStateMachine nfaStateMachine=null;
        while (!stack.empty()){
            XContentItem pop = stack.pop();
            XContentItem peek = stack.peek();
            if(pop.getNfaStateMachine()==null){
                nfaStateMachine = NfaManager.createSingleCharacterNfaStateMachine(pop.getLegend());
            }else {

            }
        }
        return stack.peek().getNfaStateMachine();
    }


}
