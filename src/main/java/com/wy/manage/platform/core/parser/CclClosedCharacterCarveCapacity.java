package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
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
        List<Integer> specialCurlyStart = context.getSpecialCurlyStart();
        if(specialCurlyStart.size()>0){
            ExceptionTools.ThrowException("]不应该在{}内");
        }
        List<Integer> specialCclStart = context.getSpecialCclStart();
        if(specialCclStart.size()==0){
            ExceptionTools.ThrowException("[]没有匹配");
        }
        Stack<XContentItem> stack = context.getStack();
        if(stack.empty()){
            ExceptionTools.ThrowException("[]没有匹配");
        }
        XContentItem pop = stack.pop();
        XContentItem peek = stack.peek();
        if(peek.getMeanType()==MeanType.CHANGE_MEANING
                && stack.peek().getLegend()==SymbolType.CCL_START.getState()){
            XContentItem pop1 = stack.pop();
            pop.addIndex(pop1.getIndex());
            pop.addIndex(i);
            //解决：([1-9][0-9]) 第二个[0-9]融合问题
            if(!stack.empty()){
                //第一个[1-9]
                NfaStateMachine nfaStateMachine66 = stack.peek().getNfaStateMachine();
                //不等于空，则保证不是[({
                if(nfaStateMachine66!=null){
                    //然后取值出栈
                    XContentItem pop2 = stack.pop();
                    NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(pop2.getNfaStateMachine(), pop.getNfaStateMachine());
                    pop.setNfaStateMachine(linkNfaStateMachine);
                    pop.addIndex(pop2.getIndex());
                }
            }

            stack.push(pop);
            specialCclStart.remove(specialCclStart.size()-1);
            List<Integer> specialAtBol = context.getSpecialAtBol();
            if(specialAtBol.size()>0){
                EdgeLine edgeLines = pop.getNfaStateMachine().getStartNode().getEdgeLines()[0];
                List<Character> edgeAllowInputGather = edgeLines.getEdgeAllowInputGather();
                List<Character> list=new ArrayList<Character>();
                for(int y=0;y<128;y++){
                    if(y==123){
                        System.out.print("ss");
                    }
                    if(!edgeAllowInputGather.contains((char)y)){
                        list.add((char)y);
                    }
                }
                edgeLines.setEdgeAllowInputGather(list);

            }

        }else {
            ExceptionTools.ThrowException("[]解析错误");
        }

        return 0;
    }
}
