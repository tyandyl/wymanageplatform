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
                System.out.println("打印:"+SelectorType.ID_SELECTOR.name());
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine invokeSpot = new InvokerImpl("\\.").relevance(new RelevanceHandle<CssBag>() {
            public void handle(ModelParam modelParam,CssBag cssBag) {
                cssBag.setSelectorType(SelectorType.CLASS_SELECTOR);
                System.out.println("打印:"+SelectorType.CLASS_SELECTOR.name());
            }
        }).setIsPrint(true).invoke();

        //解析 #|.
        NfaStateMachine orNfaStateMachine = NfaManager.createOrNfaStateMachine(invokePound, invokeSpot);

        //解析(.)+\{
        NfaStateMachine invokeFirstFollowUp = new InvokerImpl("[^{ ]+\\{").relevance(new RelevanceHandle<CssBag>() {
            public void handle(ModelParam modelParam,CssBag cssBag) {
                List<Character> curModelValue = modelParam.getCurModelValue();
                curModelValue.remove(curModelValue.size() - 1);
                StringBuilder str = new StringBuilder();
                for (Character character : curModelValue) {// 对ArrayList进行遍历，将字符放入StringBuilder中
                    str.append(character);
                }
                cssBag.setName(String.valueOf(str));
                System.out.println("打印:"+cssBag.getName());
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(orNfaStateMachine, invokeFirstFollowUp);

        //定义css解析的正则表达式position:static
        NfaStateMachine invokeStatic = new InvokerImpl("position:static").relevance(new RelevanceHandle<CssBag>() {
            public void handle(ModelParam modelParam,CssBag cssBag) {
                Map<String, String> map = cssBag.getMap();
                map.put("position","static");
                System.out.println("打印:position:static");
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine invokeRelative = new InvokerImpl("position:relative").relevance(new RelevanceHandle<CssBag>() {
            public void handle(ModelParam modelParam,CssBag cssBag) {
                Map<String, String> map = cssBag.getMap();
                map.put("position","relative");
                System.out.println("打印:position:relative");
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine orNfaStateMachine1 = NfaManager.createOrNfaStateMachine(invokeStatic, invokeRelative);

        NfaStateMachine linkNfaStateMachine1 = NfaManager.createLinkNfaStateMachine(linkNfaStateMachine, orNfaStateMachine1);



        return linkNfaStateMachine1;
    }
}
