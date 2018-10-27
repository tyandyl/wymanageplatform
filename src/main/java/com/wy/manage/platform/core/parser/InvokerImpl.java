package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by tianye
 */
public class InvokerImpl implements Invoker {
    private String regularStr;
    private long sTid = 0L;
    private boolean isPrint = false;
    //打标记使用，看状态码在哪个empty集合中。
    private boolean isMark = false;

    private RelevanceHandle handle;

    private String handleName;

    public InvokerImpl(String regularStr) {
        this.regularStr = regularStr;
    }


    public InvokerImpl relevance(RelevanceHandle handle){
        this.handle=handle;
        return this;
    }

    public InvokerImpl setIsPrint(boolean isPrint){
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

        if(StringUtils.isNotBlank(handleName)){
            endNode.setHandleName(handleName);
        }
        return nfaStateMachine;
    }


    private NfaStateMachine preInvoke() throws Exception {
        Stack<XContentItem> stack = RegularExpressionParser.parserCss(regularStr.toCharArray());
        if (stack.size() == 1) {
            XContentItem peek = stack.peek();
            NfaStateMachine nfaStateMachine = peek.getNfaStateMachine();
            if (this.isPrint) {
                System.out.println("开始解析正则表达式:" + regularStr);
            }
            if(this.isMark){
                System.out.println("开始节点的状态码是:" + nfaStateMachine.getStartNode().getStateNum());
                nfaStateMachine.getStartNode().setMark(true);
            }

            return nfaStateMachine;
        } else {
            System.out.println("栈的长度是:" + stack.size());
            System.out.println("栈末级项的内容是:" + stack.peek().getIndex());
            ExceptionTools.ThrowException("解析失败,请检查");
        }
        return null;

    }

    public String getHandleName() {
        return handleName;
    }

    public InvokerImpl setHandleName(String handleName) {
        this.handleName = handleName;
        return this;
    }

    public boolean isMark() {
        return isMark;
    }

    public InvokerImpl setMark(boolean mark) {
        isMark = mark;
        return this;
    }
}
