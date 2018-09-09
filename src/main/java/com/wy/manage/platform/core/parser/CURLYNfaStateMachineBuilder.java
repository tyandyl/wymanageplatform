package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/9.
 */
public class CURLYNfaStateMachineBuilder extends NfaStateMachineBuilder{

    public List<Character> getCharacterRepertoire(Stack<Integer> stack,Stack<XContentItem> stackXContentItem) throws Exception {
        if(stackXContentItem.empty()){
            throw new Exception("{前边不允许为空");
        }
        XContentItem peek = stackXContentItem.peek();
        List<Character> characterList = new ArrayList<Character>();
        if(peek.getNfaStateMachine()==null){
            characterList.add((char)(peek.getLegend()));
        }else {
            //初步认为外侧是最简单状态机
            characterList.addAll(peek.getNfaStateMachine().getStartNode().getEdgeLines()[0].getEdgeAllowInputGather());
        }
        Integer pop = stack.pop();
        Integer peek1 =null;
        if(!stack.empty()){
            peek1 = stack.peek();
        }
        if(!Character.isDigit(pop)){
            throw new Exception("{里边不允许存放不是数字的东西");
        }
        if(peek1==null){

        }
        return null;
    }

    public NfaStateMachine createNfaStateMachine(List<Character> list,int least,int max) throws Exception {
        return null;
    }
}
