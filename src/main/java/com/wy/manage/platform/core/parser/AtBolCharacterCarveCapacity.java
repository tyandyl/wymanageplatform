package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/17.
 */
public class AtBolCharacterCarveCapacity implements CharacterCarveCapacity{

    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
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
                    throw new Exception("^必须在[]中排首位");
                }
            }else {
                int size = specialParenStart.size();
                if(size==stack.size()){
                    //正则表达式的首位，默认不处理
                }else {
                    throw new Exception("^必须在正则表达式中排首位");
                }
            }
        }
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            throw new Exception("^不能再{}中");
        }

        return 0;
    }
}
