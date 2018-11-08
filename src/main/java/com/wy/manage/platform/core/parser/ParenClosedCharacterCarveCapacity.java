package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.action.Action;
import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.*;

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
                if(specialParenStart.size()==0){
                    boolean or = context.isOr();
                    //或者情况如(\.|#)，需要回退，并将最大index标记
                    if(or){
                        return i;
                    }else {
                        ExceptionTools.ThrowException("()没有匹配");
                    }
                }
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
                    specialParenStart.remove(specialParenStart.size()-1);
                    Set<Integer> index = xContentItemParen.getIndex();
                    StringBuffer str=new StringBuffer();
                    for(Integer w:index){
                        str.append(array[w]);
                    }
                    String substring = str.toString().substring(1, str.toString().length() - 1);

                    //System.out.println("打印动作:"+substring);
                    Map<String, Action> actions = context.getActions();
                    Action action = actions.get(substring);
                    if(action!=null){
                        //System.out.println("打印动作:"+action.getName());
                        xContentItemParen.getNfaStateMachine().getEndNode().setAction(action);
                    }
                    //(([\r\n\s]*)position([\r\n\s]*)解决这种，(前边是position，需要合并
                    if(!stack.empty()){
                        XContentItem peek = stack.peek();
                        NfaStateMachine nfaStateMachine1 = peek.getNfaStateMachine();
                        if(nfaStateMachine1!=null){
                            XContentItem pop2 = stack.pop();
                            NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop2.getNfaStateMachine(), xContentItemParen.getNfaStateMachine());
                            xContentItemParen.setNfaStateMachine(linkNfaStateMachine);
                            xContentItemParen.addIndex(pop2.getIndex());
                        }
                    }

                    stack.push(xContentItemParen);
                }else {
                    ExceptionTools.ThrowException("()解析失败,前一位不是(");
                }


            }
        }

        return 0;

    }
}
