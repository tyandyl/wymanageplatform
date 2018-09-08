package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/8.
 */
public class RegularExpressionParser {

    public static NfaStateMachine parser(String str) throws Exception {
        char[] arry=str.toCharArray();
        NfaStateMachine nfaStateMachine=null;
        Stack<XContentItem> stack=new Stack<XContentItem>();
        for(int i=0;i<arry.length;i++){
            SymbolType symbolType = SymbolType.findSymbolType(arry[i]);
            switch (symbolType){
                case CCL_END:
                    analyze(stack,new NfaStateMachineBuilder(){
                        public List<Character> getCharacterRepertoire(Stack<Integer> stack) throws Exception {
                            while (!stack.empty()){
                                Integer pop = stack.pop();
                            }
                            return null;
                        }

                        public NfaStateMachine createNfaStateMachine(List<Character> list) throws Exception {
                            return null;
                        }
                    });
                    break;
                    default:
                        break;
            }
        }
        return null;
    }

    public static void analyze(Stack<XContentItem> stack,NfaStateMachineBuilder nfaStateMachineBuilder)throws Exception{
        boolean isEmpty = stack.empty();
        if(isEmpty){
            throw new Exception("]必须和[成对");
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
            throw new Exception("]必须和[成对");
        }
        boolean empty = stack1.empty();
        if(empty){
            throw new Exception("]和[之间必须有字符");
        }
        nfaStateMachineBuilder.builder(stack1);


    }

}
