package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class ParenClosedCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            ExceptionTools.ThrowException(")不应该在{}内");
        }
        List<Integer> specialParenStart = context.getSpecialParenStart();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemParen=new XContentItem(array[i],i);
        if(specialParenStart.size()==0){
            ExceptionTools.ThrowException("()没有匹配");
        }
        Stack<XContentItem> stack = context.getStack();
        if(stack.empty()){
            ExceptionTools.ThrowException("()没有匹配");
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemParen.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    xContentItemParen.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemParen);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.CLOSE_PAREN.getState());
                    xContentItemParen.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemParen.addIndex(pop.getIndex());
                    stack.push(xContentItemParen);
                }
            }else{
                xContentItemParen.setMeanType(MeanType.CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                if(pop.getNfaStateMachine()==null){
                    ExceptionTools.ThrowException("()解析失败,前一位不能是空");
                }
                xContentItemParen.setNfaStateMachine(nfaStateMachine);
                xContentItemParen.addIndex(pop.getIndex());
                xContentItemParen.addIndex(i);
                XContentItem pop1 = stack.pop();
                if(pop1.getMeanType()==MeanType.CHANGE_MEANING
                        &&pop1.getLegend()==SymbolType.OPEN_PAREN.getState()){
                    xContentItemParen.addIndex(pop1.getIndex());
                    stack.push(xContentItemParen);
                    specialParenStart.remove(specialParenStart.size()-1);
                }else {
                    ExceptionTools.ThrowException("()解析失败,前一位不是(");
                }


            }
        }

        return 0;

    }
}
