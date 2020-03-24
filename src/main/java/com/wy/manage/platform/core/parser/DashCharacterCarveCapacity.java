package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class DashCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            ExceptionTools.ThrowException("-不应该在{}内");
        }
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
                if(Character.isLetterOrDigit(array[i+1])){
                    if(pop.getLegend()>=(int)array[i+1]){
                        ExceptionTools.ThrowException("不允许-后比-前小");
                    }
                    xContentItemDash.addIndex(i+1);
                    NfaStateMachine nfaStateMachine=null;
                    for (int y=pop.getLegend()+1;y<=(int)array[i+1];y++){
                        nfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(pop.getNfaStateMachine(), y);
                    }
                    xContentItemDash.setNfaStateMachine(nfaStateMachine);
                    stack.push(xContentItemDash);
                    return 1;
                }else {
                    //解决[0-9a-z-] 最后一个-代表或许
                    NfaStateMachine nfaStateMachine = pop.getNfaStateMachine();
                    if(nfaStateMachine!=null){
                        nfaStateMachine = NfaManager.createCharacterRepertoireNfaStateMachine(pop.getNfaStateMachine(), SymbolType.DASH.getState());
                        xContentItemDash.setNfaStateMachine(nfaStateMachine);
                        stack.push(xContentItemDash);
                    }else {
                        //解决[-]
                        NfaStateMachine singleCharacterNfaStateMachine = NfaManager.createSingleCharacterNfaStateMachine(array[i]);
                        xContentItemDash.setNfaStateMachine(singleCharacterNfaStateMachine);
                        stack.push(xContentItemDash);
                    }
                }

            }else {
                xContentItemDash.setMeanType(MeanType.CHANGE_MEANING);
                XContentItem pop = stack.pop();
                NfaStateMachine linkNfaStateMachine=null;
                if(pop.getNfaStateMachine()==null){
                    stack.push(pop);
                    linkNfaStateMachine=NfaManager.createSingleCharacterNfaStateMachine(array[i]);
                }else{
                    linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop.getNfaStateMachine(), NfaManager.createSingleCharacterNfaStateMachine(array[i]));
                }
                xContentItemDash.setNfaStateMachine( linkNfaStateMachine);
                xContentItemDash.addIndex(pop.getIndex());
                stack.push(xContentItemDash);
            }

        }
        return 0;
    }
}
