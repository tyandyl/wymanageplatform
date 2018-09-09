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
                    NfaStateMachine builder = cclNfaStateMachineBuilder.builder(stack, "[", "]");
                    XContentItem xContentItem1 = new XContentItem(builder);
                    stack.add(xContentItem1);
                    break;
                default:
                    XContentItem xContentItem=new XContentItem(array[i],i);
                    stack.add(xContentItem);
                    break;
            }

        }
        return stack.peek().getNfaStateMachine();
    }


}
