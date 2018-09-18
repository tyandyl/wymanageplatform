package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/17.
 */
public class DashCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        XContentItem xContentItemDash=new XContentItem(array[i],i);
        if(stack.empty()){
            xContentItemDash.setNfaStateMachine( NfaManager.createSingleCharacterNfaStateMachine(array[i]));
            xContentItemDash.setMeanType(MeanType.CHANGE_MEANING);
            stack.push(xContentItemDash);
        }else {
            if(specialCclStart.size()>0){
                //-前边的字符
                XContentItem pop = stack.pop();
                xContentItemDash.addIndex(pop.getIndex());
                if(pop.getLegend()>=(int)array[i+1]){
                    throw new Exception("不允许-后比-前小");
                }
                NfaStateMachine nfaStateMachine=null;
                for (int y=pop.getLegend();y<=(int)array[i+1];y++){
                    nfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(pop.getNfaStateMachine(), y);
                }
                xContentItemDash.setNfaStateMachine(nfaStateMachine);
                return 1;
            }else {
                xContentItemDash.setMeanType(MeanType.CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                xContentItemDash.setNfaStateMachine( linkNfaStateMachine);
                xContentItemDash.addIndex(pop.getIndex());
                stack.push(xContentItemDash);
            }

        }
        return 0;
    }
}
