package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.CssTools;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.widget.IFlow;
import com.wy.manage.platform.core.widget.NormalFlow;
import com.wy.manage.platform.core.widget.SelectorType;

import java.util.*;

/**
 * Created by tianye
 */
public class CssParser {

    public static NfaStateMachine parser()throws Exception{
        Properties properties = CssTools.getProperties();
        //重写，保证读取顺序
        Set<String> strings = properties.stringPropertyNames();
        Map<String,NfaStateMachine> map=new HashMap<String, NfaStateMachine>();
        NfaStateMachine nfaStateMachine=null;
        for (String str:strings) {
            String name =str;
            String value = properties.getProperty(str);
            if (value != null) {
                if(value.contains(" ")){
                    String[] split = value.split(" ");
                    if(split!=null && split.length!=0){

                        for(int i=0;i<split.length;i++){
                            NfaStateMachine nfaM = map.get(split[i]);
                            nfaStateMachine = link(nfaStateMachine, nfaM);
                        }
                        map.put(name,nfaStateMachine);
                    }
                }else {
                    map.put(name,getNfaStateMachine(name, value));
                }
            }
        }

        return nfaStateMachine;
    }

    public static NfaStateMachine link(NfaStateMachine link1,NfaStateMachine link2)throws Exception{
        if(link1==null){
            return link2;
        }
        if(link2==null){
            return link1;
        }
        return NfaManager.createLinkNfaStateMachine(link1,link2);
    }

    public static NfaStateMachine getNfaStateMachine(String name,String value)throws Exception{
        if("ignore".equalsIgnoreCase(name)){
            return new InvokerImpl(value).relevance(new RelevanceHandle<CssBag>() {
                public void handle(ModelParam modelParam,CssBag cssBag) {
                }
            }).setIsPrint(true).invoke();

        }else if("attributeTag".equalsIgnoreCase(name)){
            return new InvokerImpl(value).relevance(new RelevanceHandle<CssBag>() {
                public void handle(ModelParam modelParam,CssBag cssBag) {
                    List<Character> curModelValue = modelParam.getCurModelValue();
                    if(curModelValue!=null && curModelValue.size()==1){
                        Character character = curModelValue.get(0);
                        if(character==46){
                            cssBag.setSelectorType(SelectorType.ID_SELECTOR);
                        }else if(character==35){
                            cssBag.setSelectorType(SelectorType.ID_SELECTOR);
                        }
                    }
                }
            }).setIsPrint(true).invoke();

        }else if("attributeName".equalsIgnoreCase(name)){
            return new InvokerImpl(value).relevance(new RelevanceHandle<CssBag>() {
                public void handle(ModelParam modelParam,CssBag cssBag) {
                    List<Character> curModelValue = modelParam.getCurModelValue();
                    StringBuilder str = new StringBuilder();
                    for (Character character : curModelValue) {// 对ArrayList进行遍历，将字符放入StringBuilder中
                        str.append(character);
                    }
                    cssBag.setName(String.valueOf(str));
                }
            }).setIsPrint(true).invoke();

        }else if("attributeFirstOpenCurly".equalsIgnoreCase(name)){
            return new InvokerImpl(value).relevance(new RelevanceHandle<CssBag>() {
                public void handle(ModelParam modelParam,CssBag cssBag) {
                }
            }).setIsPrint(true).invoke();
        }
        ExceptionTools.ThrowException("没找到相应的正则表达式");
        return null;

    }
}
