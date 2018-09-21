package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.widget.IFlow;
import com.wy.manage.platform.core.widget.NormalFlow;
import com.wy.manage.platform.core.widget.SelectorType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class CssParser {

    public static NfaStateMachine parser()throws Exception{

        NfaStateMachine invokePound = new InvokerImpl("#").relevance(new RelevanceHandle<CssBag>() {
            public void handle(ModelParam modelParam,CssBag cssBag) {
                cssBag.setSelectorType(SelectorType.ID_SELECTOR);
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine invokeSpot = new InvokerImpl("\\.").relevance(new RelevanceHandle<CssBag>() {
            public void handle(ModelParam modelParam,CssBag cssBag) {
                cssBag.setSelectorType(SelectorType.CLASS_SELECTOR);
            }
        }).setIsPrint(true).invoke();

        //解析 #|.
        NfaStateMachine orNfaStateMachine = NfaManager.createOrNfaStateMachine(invokePound, invokeSpot);

        //解析(.)+\{
        NfaStateMachine invokeFirstFollowUp = new InvokerImpl("(.)+\\{").relevance(new RelevanceHandle<CssBag>() {
            public void handle(ModelParam modelParam,CssBag cssBag) {
                List curModelValue = modelParam.getCurModelValue();
                List value = (List)curModelValue.remove(curModelValue.size() - 1);
                Character[] objects = (Character[])value.toArray();
                cssBag.setName(String.valueOf(objects));
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(orNfaStateMachine, invokeFirstFollowUp);


        return linkNfaStateMachine;
    }
}
