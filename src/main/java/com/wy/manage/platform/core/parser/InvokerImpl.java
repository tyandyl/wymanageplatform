package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.AtomicTools;
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
        //System.out.println("当前正则是:"+handleName+"，其包含节点有:");
        final Integer num = AtomicTools.getBiUniqueInteger();
        System.out.println("状态机:"+handleName+"的统一标识符是:"+num);
        NfaManager.traverse(nfaStateMachine.getStartNode(),new NodeHandle<NfaStateNode>(){
                public void handle(NfaStateNode o) throws Exception {
                    o.setHandleName(handleName);
                    if(o.getObjectId()!=num){
                        o.setObjectId(num);
                       // System.out.println(o.getStateNum()+",他是"+o.getHandleName()+"节点,其状态是:"+o.getState().getName());
                        if(o.getStateNum().equalsIgnoreCase("153")){
                           // System.out.println("153");
                        }
                    }
                }
            }, num);

        return nfaStateMachine;
    }


    private NfaStateMachine preInvoke() throws Exception {
        Stack<XContentItem> stack = RegularExpressionParser.parserCss(regularStr.toCharArray());
        if (stack.size() == 1) {
            XContentItem peek = stack.peek();
            NfaStateMachine nfaStateMachine = peek.getNfaStateMachine();

            if (this.isPrint) {
                //System.out.println("开始解析正则表达式:" + handleName);
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
}
