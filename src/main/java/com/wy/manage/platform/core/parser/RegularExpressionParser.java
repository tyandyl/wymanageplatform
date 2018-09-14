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
        boolean isCCL_START=false;
        for(int i=0;i<array.length;i++){
            SymbolType symbolType = SymbolType.findSymbolType(array[i]);
            switch (symbolType){
                case ANY:
                    //.不知道是连接还是或，在外边是连接在[.]是或的意思，
                    //比如[\w./-+]
                    XContentItem xContentItemAny=new XContentItem(array[i],i);
                    if(isCCL_START){
                        xContentItemAny.setMeanType(MeanType.NO_CHANGE_MEANING);
                    }else {
                        xContentItemAny.setNfaStateMachine( NfaManager.createAnyCharacterRepertoireNfaStateMachine());
                        xContentItemAny.setMeanType(MeanType.CHANGE_MEANING);
                    }
                    stack.add(xContentItemAny);
                    break;
                case AT_BOL:
                    XContentItem xContentItemAtBol=new XContentItem(array[i],i);
                    xContentItemAtBol.setMeanType(MeanType.CHANGE_MEANING);
                    stack.add(xContentItemAtBol);
                    break;
                case AT_EOL:
                    XContentItem xContentItemAtEol=new XContentItem(array[i],i);
                    //只要在[]中，不管加不加()，都是原值
                    if(isCCL_START){
                        xContentItemAtEol.setMeanType(MeanType.NO_CHANGE_MEANING);
                    }else {
                        xContentItemAtEol.setMeanType(MeanType.CHANGE_MEANING);
                    }
                    stack.add(xContentItemAtEol);
                    break;
                case CCL_END:
                    NfaStateMachineBuilder cclNfaStateMachineBuilder = NfaStateMachineFactory.getCCLNfaStateMachineBuilder();
                    cclNfaStateMachineBuilder.builder(stack, array,i,SymbolType.CCL_END, SymbolType.CCL_START);
                    isCCL_START=false;
                    break;
                case CCL_START:
                    isCCL_START=true;
                    XContentItem xContentItemCclStart=new XContentItem(array[i],i);
                    xContentItemCclStart.setMeanType(MeanType.CHANGE_MEANING);
                    stack.add(xContentItemCclStart);
                    break;
                case CLOSE_CURLY:
                    if(isCCL_START){
                        XContentItem xContentItemCloseCurly=new XContentItem(array[i],i);
                        xContentItemCloseCurly.setMeanType(MeanType.NO_CHANGE_MEANING);
                        stack.add(xContentItemCloseCurly);
                    }else {
                        NfaStateMachineBuilder curlyNfaStateMachineBuilder = NfaStateMachineFactory.getCURLYNfaStateMachineBuilder();
                        curlyNfaStateMachineBuilder.builder(stack,array,i, SymbolType.OPEN_CURLY, SymbolType.CLOSE_CURLY);
                    }
                    break;
                case OPEN_CURLY:
                    XContentItem xContentItemOpenCurly=new XContentItem(array[i],i);
                    if(isCCL_START){
                        xContentItemOpenCurly.setMeanType(MeanType.NO_CHANGE_MEANING);
                    }else {
                        xContentItemOpenCurly.setMeanType(MeanType.CHANGE_MEANING);
                    }
                    stack.add(xContentItemOpenCurly);
                    break;
                case CLOSURE:
                    XContentItem xContentItemClosure=new XContentItem(array[i],i);
                    if(isCCL_START){
                        xContentItemClosure.setMeanType(MeanType.NO_CHANGE_MEANING);
                        stack.add(xContentItemClosure);
                    }else {
                        NfaStateMachineBuilder closureNfaStateMachineBuilder = NfaStateMachineFactory.getCLOSURENfaStateMachineBuilder();
                        closureNfaStateMachineBuilder.builder(stack,array,i,null,null);
                    }
                    break;
                case DASH:
                    XContentItem xContentItemDash=new XContentItem(array[i],i);
                    if(isCCL_START){
                        xContentItemDash.setMeanType(MeanType.CHANGE_MEANING);
                    }else {
                        xContentItemDash.setMeanType(MeanType.NO_CHANGE_MEANING);
                    }
                    stack.add(xContentItemDash);
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
                nfaStateMachine = NfaManager.createLinkNfaStateMachine(nfaStateMachine, nfaStateMachineItem);
            }
        }
        return nfaStateMachine;
    }


}
