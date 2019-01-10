package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.action.Action;
import com.wy.manage.platform.core.utils.AtomicTools;
import com.wy.manage.platform.core.utils.ExceptionTools;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by tianye
 */
public class InvokerImpl<T> implements Invoker<T> {
    private String regularStr;
    private long sTid = 0L;
    private boolean isPrint = false;


    public T invoke() {
        return null;
    }


    private NfaStateMachine onInvoke(NfaStateMachine nfaStateMachine,RelevanceHandle handle) throws Exception{
//        NfaStateNode endNode = nfaStateMachine.getEndNode();
//        endNode.setHandle(handle);
        //System.out.println("当前正则是:"+handleName+"，其包含节点有:");
        final Integer num = AtomicTools.getBiUniqueInteger();
        NfaManager.traverse(nfaStateMachine.getStartNode(),new NodeHandle<NfaStateNode>(){
                public void handle(NfaStateNode o) throws Exception {
                    if(o.getObjectId()!=num){
                        o.setObjectId(num);
                       // System.out.println(o.getStateNum()+",他是"+o.getHandleName()+"节点,其状态是:"+o.getState().getName());
                    }
                }
            }, num);

        return nfaStateMachine;
    }


}
