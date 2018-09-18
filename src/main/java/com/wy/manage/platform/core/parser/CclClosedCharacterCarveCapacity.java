package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye13 on 2018/9/9.
 */
public class CclClosedCharacterCarveCapacity implements CharacterCarveCapacity{

    /**
     * 我们要解决[0-9],[a-z],[a-zA-Z],[A-Za-z0-9]
     * [\w./-+]是匹配\w [0-9a-zA-Z_] 或 . 或 / 或 - 或 + 字符；
     * 在[./-+]内均表示字符本身
     * 在[.]内点，不是任意字符的意思，就是匹配点.字符本身，点.可以不需要加反斜杠\.
     * 在[]内特殊字符，表示匹配特殊字符本身，不需要加反斜杠
     * 在[]外特殊字符，表示匹配特殊字符本身，必须要加反斜杠
     * @param context
     * @param array
     * @param i
     * @throws Exception
     */
    public int carve(CharacterCarveContext context, char[] array, int i) throws Exception {
        List<Integer> specialCclStart = context.getSpecialCclStart();
        if(specialCclStart.size()==0){
            throw new Exception("[]没有匹配");
        }
        Stack<XContentItem> stack = context.getStack();
        if(stack.empty()){
            throw new Exception("[]没有匹配");
        }
        XContentItem pop = stack.pop();
        XContentItem peek = stack.peek();
        if(peek.getMeanType()==MeanType.CHANGE_MEANING
                && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
            XContentItem pop1 = stack.pop();
            pop.addIndex(pop1.getIndex());
            pop.addIndex(i);
            stack.push(pop);
            specialCclStart.remove(specialCclStart.size()-1);
        }else {
            throw new Exception("[]解析错误");
        }

        return 0;
    }
}
