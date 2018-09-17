package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/17.
 */
public class DashCharacterCarveCapacity implements CharacterCarveCapacity{

    public void carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemDash=new XContentItem(array[i],i);
        if(stack.empty()){
            xContentItemDash.setNfaStateMachine( NfaManager.createSingleCharacterNfaStateMachine(array[i]));
            xContentItemDash.setMeanType(MeanType.CHANGE_MEANING);
            stack.push(xContentItemDash);
        }else {
            if(specialCclStart.size()>0){
                XContentItem peek = stack.peek();
                if(peek.getMeanType()==MeanType.NO_CHANGE_MEANING
                        && Character.isLetterOrDigit(peek.getLegend())){
                    peek.setDash(true);
                }else {
                    throw new Exception("{-前边必须是字母或者数字");
                }
            }else {
                xContentItemDash.setMeanType(MeanType.CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                xContentItemDash.setNfaStateMachine( linkNfaStateMachine);
                xContentItemDash.addIndex(pop.getIndex());
                stack.push(xContentItemDash);
            }

        }
    }
}
