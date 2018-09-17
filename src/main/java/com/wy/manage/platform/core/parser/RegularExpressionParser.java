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
        CharacterCarveContext context=new CharacterCarveContext();
        for(int i=0;i<array.length;i++){
            SymbolType symbolType = SymbolType.findSymbolType(array[i]);
            switch (symbolType){
                case ANY:
                    CharacterCarveCapacity anyCharacterCarveCapacity = NfaStateMachineFactory.getAnyCharacterCarveCapacity();
                    anyCharacterCarveCapacity.carve(context,array,i);
                    break;
                case AT_BOL:
                    CharacterCarveCapacity atBolCharacterCarveCapacity = NfaStateMachineFactory.getAtBolCharacterCarveCapacity();
                    atBolCharacterCarveCapacity.carve(context,array,i);
                    break;
                case AT_EOL:
                    CharacterCarveCapacity atEolCharacterCarveCapacity = NfaStateMachineFactory.getAtEolCharacterCarveCapacity();
                    atEolCharacterCarveCapacity.carve(context,array,i);
                    break;
                case CCL_END:
                    CharacterCarveCapacity cclCharacterCarveCapacity = NfaStateMachineFactory.getCclCharacterCarveCapacity();
                    cclCharacterCarveCapacity.carve(context,array,i);
                    break;
                case CCL_START:
                    List<Integer> specialCclStart = context.getSpecialCclStart();
                    Stack<XContentItem> stack = context.getStack();
                    XContentItem xContentItemCclStart=new XContentItem(array[i],i);
                    xContentItemCclStart.setMeanType(MeanType.CHANGE_MEANING);
                    stack.add(xContentItemCclStart);
                    specialCclStart.add(i);
                    break;
                case CLOSE_CURLY:
                    CharacterCarveCapacity curlyCharacterCarveCapacity = NfaStateMachineFactory.getCURLYCharacterCarveCapacity();
                    curlyCharacterCarveCapacity.carve(context,array,i);
                    break;
                case OPEN_CURLY:
                    List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
                    specialCurlyStart.add(i);
                    XContentItem xContentItemOpenCurly=new XContentItem(array[i],i);
                    xContentItemOpenCurly.setMeanType(MeanType.CHANGE_MEANING);
                    Stack<XContentItem> stackCurly = context.getStack();
                    stackCurly.add(xContentItemOpenCurly);
                    break;
                case CLOSURE:
                    CharacterCarveCapacity closureNfaStateMachineBuilder = NfaStateMachineFactory.getCLOSURECharacterCarveCapacity();
                    closureNfaStateMachineBuilder.carve(context,array,i);
                    break;
                case DASH:
                    CharacterCarveCapacity dashCharacterCarveCapacity = NfaStateMachineFactory.getDashCharacterCarveCapacity();
                    dashCharacterCarveCapacity.carve(context,array,i);
                    break;
                case OPTIONAL:
                    XContentItem xContentItemOptional=new XContentItem(array[i],i);
                    if(isCCL_START){
                        xContentItemOptional.setMeanType(MeanType.NO_CHANGE_MEANING);
                        stack.add(xContentItemOptional);
                    }else {
                        NfaStateMachineBuilder optionalNfaStateMachineBuilder = NfaStateMachineFactory.getOPTIONALNfaStateMachineBuilder();
                        optionalNfaStateMachineBuilder.builder(stack,array,i,null,null);
                    }
                    break;
                case OR:
                    if(isCCL_START){
                        XContentItem xContentItemOr=new XContentItem(array[i],i);
                        xContentItemOr.setMeanType(MeanType.NO_CHANGE_MEANING);
                        stack.add(xContentItemOr);
                    }else {
                        NfaStateMachineBuilder orNfaStateMachineBuilder = NfaStateMachineFactory.getORNfaStateMachineBuilder();
                        orNfaStateMachineBuilder.builder(stack,array,i,null,null);
                    }
                    break;
                case BACKSLASH:
                    XContentItem xContentItemL=new XContentItem(array[i+1],i+1,MeanType.NO_CHANGE_MEANING);
                    stack.add(xContentItemL);
                    i++;
                    break;
                case OPEN_PAREN:
                    XContentItem xContentItemOpenParen=new XContentItem(array[i],i);
                    if(isCCL_START){
                        xContentItemOpenParen.setMeanType(MeanType.NO_CHANGE_MEANING);
                    }else {
                        xContentItemOpenParen.setMeanType(MeanType.CHANGE_MEANING);
                    }
                    stack.add(xContentItemOpenParen);
                    break;
                case CLOSE_PAREN:
                    if(isCCL_START){
                        XContentItem xContentItemCloseParen=new XContentItem(array[i],i);
                        xContentItemCloseParen.setMeanType(MeanType.NO_CHANGE_MEANING);
                        stack.add(xContentItemCloseParen);
                    }else {
                        NfaStateMachineBuilder parenNfaStateMachineBuilder = NfaStateMachineFactory.getPARENNfaStateMachineBuilder();
                        parenNfaStateMachineBuilder.builder(stack,array,i,SymbolType.OPEN_PAREN,SymbolType.CLOSE_PAREN);
                    }
                    break;
                default:
                    //普通字符
                    XContentItem xContentItem=new XContentItem(array[i],i);
                    xContentItem.setMeanType(MeanType.NO_CHANGE_MEANING);
                    stack.add(xContentItem);
                    break;
            }

        }
        Stack<XContentItem> stackForward=new Stack<XContentItem>();
        while (!stack.empty()){
            XContentItem pop = stack.pop();
            stackForward.push(pop);
        }

        NfaStateMachine nfaStateMachine=null;
        while (!stackForward.empty()){
            XContentItem pop = stackForward.pop();
            NfaStateMachine nfaStateMachineItem=null;
            if(pop.getNfaStateMachine()==null){
                if(pop.getLegend()==SymbolType.ANY.getState()){
                    nfaStateMachineItem=NfaManager.createAnyCharacterRepertoireNfaStateMachine();
                }else {
                    nfaStateMachineItem = NfaManager.createSingleCharacterNfaStateMachine(pop.getLegend());
                }

            }else {
                nfaStateMachineItem=pop.getNfaStateMachine();
            }
            if(nfaStateMachine==null){
                nfaStateMachine=nfaStateMachineItem;
            }else {
                nfaStateMachine = NfaManager.createLinkNfaStateMachine();
            }
        }
        return nfaStateMachine;
    }


}
