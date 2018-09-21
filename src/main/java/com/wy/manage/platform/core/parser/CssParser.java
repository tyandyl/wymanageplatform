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
        NfaStateMachine invokeFirstFollowUp = new InvokerImpl("[a-z1-9A-Z]+\\{").relevance(new RelevanceHandle<CssBag>() {
            public void handle(ModelParam modelParam,CssBag cssBag) {
                List<Character> curModelValue = modelParam.getCurModelValue();
                curModelValue.remove(curModelValue.size() - 1);
                StringBuilder str = new StringBuilder();
                for (Character character : curModelValue) {// 对ArrayList进行遍历，将字符放入StringBuilder中
                    str.append(character);
                }
                cssBag.setName(String.valueOf(str));
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(orNfaStateMachine, invokeFirstFollowUp);


        return linkNfaStateMachine;
    }
}
