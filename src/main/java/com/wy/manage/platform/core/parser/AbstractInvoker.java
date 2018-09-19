package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.Stack;

/**
 * Created by tianye
 */
public class AbstractInvoker implements Invoker {
    private String regularStr;
    private long sTid = 0L;
    private boolean isPrint = false;
    private RelevanceHandle handle;

    public AbstractInvoker(String regularStr) {
        this.regularStr = regularStr;
    }

    public AbstractInvoker relevance(RelevanceHandle handle){
        this.handle=handle;
        return this;
    }

    public AbstractInvoker setIsPrint(boolean isPrint){
        this.isPrint=isPrint;
        return this;
    }


    public NfaStateMachine invoke() {
        NfaStateMachine t=null;
        try {
            this.sTid = System.currentTimeMillis();
            t=this.onInvoke(this.preInvoke(),handle);
        } catch (Exception e) {
        } finally {
        }
        return t;
    }

    private NfaStateMachine onInvoke(NfaStateMachine nfaStateMachine,RelevanceHandle handle) throws Exception{
        NfaStateNode endNode = nfaStateMachine.getEndNode();
        endNode.setHandle(handle);
        return nfaStateMachine;
    }

    private NfaStateMachine preInvoke() throws Exception {
        if (this.isPrint) {
            System.out.println("开始解析正则表达式:" + regularStr);
        }
        Stack<XContentItem> stack = RegularExpressionParser.parserCss(regularStr.toCharArray());
        if (stack.size() == 1) {
            XContentItem peek = stack.peek();
            return peek.getNfaStateMachine();
        } else {
            System.out.println("栈的长度是:" + stack.size());
            System.out.println("栈末级项的内容是:" + stack.peek().getIndex());
            ExceptionTools.ThrowException("解析失败,请检查");
        }
        return null;

    }
}
