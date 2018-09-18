package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/18.
 */
public class CommaCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemComma=new XContentItem(array[i],i);
        if(stack.empty()){
            xContentItemComma.setNfaStateMachine( NfaManager.createSingleCharacterNfaStateMachine(array[i]));
            xContentItemComma.setMeanType(MeanType.CHANGE_MEANING);
            stack.push(xContentItemComma);
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                xContentItemComma.setMeanType(MeanType.NO_CHANGE_MEANING);
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
                    xContentItemComma.setNfaStateMachine(NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    stack.push(xContentItemComma);
                }else {
                    XContentItem pop = stack.pop();
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    NfaStateMachine characterRepertoireNfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(nfaStateMachine,SymbolType.COMMA.getState());
                    xContentItemComma.setNfaStateMachine(characterRepertoireNfaStateMachine);
                    xContentItemComma.addIndex(pop.getIndex());
                    stack.push(xContentItemComma);
                }
            }else {
                List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
                if(specialCurlyStart.size()>0){
                    //是{的第一个字符
                    XContentItem pop = stack.pop();
                    xContentItemComma.addIndex(pop.getIndex());
                    if(!Character.isDigit(pop.getLegend())){
                        throw new Exception("{}内的第一个字符不是数字么");
                    }
                    XContentItem peek = stack.peek();
                    if(peek.getMeanType()==MeanType.CHANGE_MEANING
                            && peek.getLegend()==SymbolType.OPEN_CURLY.getState()){
                        char c = array[i + 1];
                        int c1 = (int)array[i + 2];
                        xContentItemComma.addIndex(i+1);
                        xContentItemComma.addIndex(i+2);
                        if(!Character.isDigit(c)){
                            throw new Exception("{},的下一个字符不是数字");
                        }
                        if(c1!=SymbolType.CLOSE_CURLY.getState()){
                            throw new Exception("{},的下下一个字符不是}");
                        }
                        //{
                        XContentItem pop1 = stack.pop();
                        xContentItemComma.addIndex(pop1.getIndex());
                        //{之前的字符
                        XContentItem pop2 = stack.pop();
                        xContentItemComma.addIndex(pop2.getIndex());
                        NfaStateMachine nfaStateMachine = pop2.getNfaStateMachine();
                        NfaStateMachine repetitionAddNumNfaStateMachine = NfaManager.createRepetitionAddNumNfaStateMachine(nfaStateMachine, pop.getLegend() - '0', c - '0');
                        xContentItemComma.setNfaStateMachine(repetitionAddNumNfaStateMachine);
                        stack.push(xContentItemComma);
                        return 2;
                    }else {
                        throw new Exception("{}解析中发现边不是{");
                    }

                }else {
                    xContentItemComma.setMeanType(MeanType.CHANGE_MEANING);
                    XContentItem pop = stack.pop();
                    xContentItemComma.addIndex(pop.getIndex());
                    NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                    xContentItemComma.setNfaStateMachine( linkNfaStateMachine);
                    stack.push(xContentItemComma);
                }

            }

        }

        return 0;
    }
}
