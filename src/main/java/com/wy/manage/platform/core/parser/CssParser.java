package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.widget.IFlow;
import com.wy.manage.platform.core.widget.NormalFlow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianye
 */
public class CssParser {

    public static NfaStateMachine parser()throws Exception{
        Map<String,String> map=new HashMap<String,String>();
        NfaStateMachine invokePound = new InvokerImpl("#").relevance(new RelevanceHandle<String>() {
            public String handle(ModelParam modelParam) {
                return null;
            }
        }).setIsPrint(true).invoke();
        NfaStateMachine invokeSpot = new InvokerImpl("\\.").relevance(new RelevanceHandle<String>() {
            public String handle(ModelParam modelParam) {
                return null;
            }
        }).setIsPrint(true).invoke();
        //解析 #|.
        NfaStateMachine orNfaStateMachine = NfaManager.createOrNfaStateMachine(invokePound, invokeSpot);

        //解析(.)+\{
        NfaStateMachine invokeFirstFollowUp = new InvokerImpl("(.)+\\{").relevance(new RelevanceHandle<String>() {
            public String handle(ModelParam modelParam) {
                return null;
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(orNfaStateMachine, invokeFirstFollowUp);


        return linkNfaStateMachine;
    }
}
