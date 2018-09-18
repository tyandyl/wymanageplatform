package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/9.
 */
public class CurlyClosedCharacterCarveCapacity implements CharacterCarveCapacity{


    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        Stack<XContentItem> stack = context.getStack();
        if(specialCurlyStart.size()==0){
            throw new Exception("{}没有匹配");
        }
        if(stack.empty()){
            throw new Exception("{}没有匹配");
        }
        XContentItem pop = stack.pop();
        XContentItem peek = stack.peek();
        if(peek.getMeanType()==MeanType.CHANGE_MEANING
                && stack.peek().getLegend()==SymbolType.OPEN_CURLY.getState()){
            XContentItem pop1 = stack.pop();
            pop.addIndex(i);
            pop.addIndex(pop1.getIndex());
            EdgeLine edgeLine = pop.getNfaStateMachine().getStartNode().getEdgeLines()[0];
            int least = edgeLine.getLeast();
            if(least<0){
                throw new Exception("{}第一个字母没有赋值么？");
            }
            edgeLine.setMax(array[i]-'0');
            stack.push(pop);
            specialCurlyStart.remove(specialCurlyStart.size()-1);
        }else {
            throw new Exception("{}解析错误");
        }
        return 0;

    }
}
