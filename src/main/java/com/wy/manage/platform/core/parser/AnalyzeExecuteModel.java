package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class AnalyzeExecuteModel {

    public static void execute(String str) throws Exception {
        String m="a|m";
        Stack<XContentItem> stack = RegularExpressionParser.parserCss(m.toCharArray());
        System.out.println("栈的长度是:"+stack.size());
        System.out.println("栈末级项的内容是:"+stack.peek().getIndex());
        if(stack.size()!=1){
            System.out.println("解析失败,请查找原因");
            ExceptionTools.ThrowException("解析失败,请查找原因");
        }
        char[] chars = str.toCharArray();
        NfaStateNode startNode = stack.peek().getNfaStateMachine().getStartNode();
        Stack<NfaStateNode> stackNodes=new Stack<NfaStateNode>();
//        for(int i=0;i<chars.length;i++){
//            NfaStateNode analyze = analyze(stackNodes,startNode, chars[i]);
//            if(analyze==null){
//                System.out.println("结束");
//            }else {
//                startNode=analyze;
//            }
//        }

    }

}
