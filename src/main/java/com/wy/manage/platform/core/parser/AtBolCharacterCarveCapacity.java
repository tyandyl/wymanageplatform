package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class AtBolCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            ExceptionTools.ThrowException("^不能再{}中");
        }
        Stack<XContentItem> stack = context.getStack();
        List<Integer> specialCclStart = context.getSpecialCclStart();
        List<Integer> specialAtBol1 = context.getSpecialAtBol();
        List<Integer> specialParenStart = context.getSpecialParenStart();

        XContentItem xContentItemAtBol=new XContentItem(array[i],i);
        xContentItemAtBol.setMeanType(MeanType.CHANGE_MEANING);

        if(stack.empty()){
            //正则表达式的首位，默认不处理
        }else {
            XContentItem peek = stack.peek();

            if(specialCclStart.size()>0){
                //如果位于开始
                if(peek.getMeanType()==MeanType.CHANGE_MEANING
                        && peek.getLegend()==SymbolType.CCL_START.getState()){
                    specialAtBol1.add(i);
                }else {
                    ExceptionTools.ThrowException("^必须在[]中排首位");
                }
            }else {
                int size = specialParenStart.size();
                if(size==stack.size()){
                    //正则表达式的首位，默认不处理
                }else {
                    ExceptionTools.ThrowException("^必须在正则表达式中排首位");
                }
            }
        }

        return 0;
    }
}
