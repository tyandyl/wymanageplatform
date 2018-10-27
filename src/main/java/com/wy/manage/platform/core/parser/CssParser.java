package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.CssTools;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.widget.IFlow;
import com.wy.manage.platform.core.widget.NormalFlow;
import com.wy.manage.platform.core.widget.SelectorType;
import org.apache.commons.lang.StringUtils;

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

        if(nfaStateMachine==null && map.size()==1){
            for(Map.Entry<String,NfaStateMachine> entry:map.entrySet()){
                return entry.getValue();
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
            //如果不需要关联动作，就不要显示的写，如果写了，会报错
            return new InvokerImpl(value).setHandleName("ignore").invoke();

        }else if("attributeTag".equalsIgnoreCase(name)){
            return new InvokerImpl(value).relevance(new RelevanceHandle() {
                public void handle(ModelParam modelParam) {
                    Object t = modelParam.getT();
                    if(t instanceof CssBag){
                        CssBag cssBag=(CssBag)t;
                        int curInt = modelParam.getCurInt();
                        char[] chars = modelParam.getChars();
                        char aChar = chars[curInt];
                        if(aChar==46){
                            cssBag.setSelectorType(SelectorType.ID_SELECTOR);
                        }else if(aChar==35){
                            cssBag.setSelectorType(SelectorType.ID_SELECTOR);
                        }
                    }

                }
            }).setIsPrint(true).setHandleName("attributeTag").invoke();

        }else if("attributeName".equalsIgnoreCase(name)){
            return new InvokerImpl(value).relevance(new RelevanceHandle() {
                public void handle(ModelParam modelParam) {
                    Object t = modelParam.getT();
                    if(t instanceof CssBag){
                        CssBag cssBag=(CssBag)t;
                        int curInt = modelParam.getCurInt();
                        char[] chars = modelParam.getChars();
                        char aChar = chars[curInt];
                        StringBuilder str = new StringBuilder();
                        if(StringUtils.isNotBlank(cssBag.getName())){
                            str.append(cssBag.getName());
                        }
                        str.append(aChar);
                        cssBag.setName(String.valueOf(str));
                    }

                }
            }).setIsPrint(true).setHandleName("attributeName").invoke();

        }else if("attributeFirstOpenCurly".equalsIgnoreCase(name)){
            return new InvokerImpl(value).setIsPrint(true).setMark(true).setHandleName("attributeFirstOpenCurly").invoke();
        }
        ExceptionTools.ThrowException("没找到相应的正则表达式");
        return null;

    }
}
