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
        Map<String,String> map=new HashMap<String, String>();

        NfaStateMachine nfaStateMachine=null;
        for (String str:strings) {
            String name =str;
            String value = properties.getProperty(str);
            if (value != null) {
                if(value.contains(" ")){
                    String[] split = value.split(" ");
                    if(split!=null && split.length!=0){
                        //记录当前Value有几个重复的，比如attributeFirstLine=ignore attributeTag attributeName attributeFirstOpenCurly
                        //ignore 重复必须重新创建第一个ignore节点
                        Map<String,Integer> rec=new HashMap<String, Integer>();
                        for(int i=0;i<split.length;i++){
                            String valueM = map.get(split[i]);
                            if(StringUtils.isNotBlank(valueM)){
                                nfaStateMachine = link(nfaStateMachine, getNfaStateMachine(split[i],valueM,null));
                            }else {
                                nfaStateMachine = link(nfaStateMachine, getNfaStateMachine(split[i],split[i],null));
                            }

                        }
                        getNfaStateMachine(name,null,nfaStateMachine);
                        map.put(name,value);
                    }
                }else {
                    map.put(name,value);
                }
            }
        }

        if(nfaStateMachine==null && map.size()>0){
            for(Map.Entry<String,String> entry:map.entrySet()){
                return getNfaStateMachine(entry.getKey(),entry.getValue(),null);
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

    public static NfaStateMachine getNfaStateMachine(String name,String value,NfaStateMachine nfaStateMachine)throws Exception{
        if("ignore".equalsIgnoreCase(name)){
            //如果不需要关联动作，就不要显示的写，如果写了，会报错
            return new InvokerImpl(value).setIsPrint(true).setHandleName("ignore").invoke();

        }else if("attributeTag".equalsIgnoreCase(name)){
            return new InvokerImpl(value).relevance(new RelevanceHandle() {
                public void handle(ModelParam modelParam) {
                    Object t = modelParam.getT();
                    if(t instanceof CssBag){
                        CssBag cssBag=(CssBag)t;
                        StringBuffer curModelValue = modelParam.getCurModelValue();
                        char aChar = curModelValue.charAt(0);
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
                        cssBag.setName(modelParam.getCurModelValue().toString());
                    }

                }
            }).setIsPrint(true).setHandleName("attributeName").invoke();

        }else if("positionValue".equalsIgnoreCase(name)){
            return new InvokerImpl(value)
                    .setIsPrint(true)
                    .setHandleName("positionValue")
                    .relevance(new RelevanceHandle(){
                        public void handle(ModelParam modelParam) {
                            Object t = modelParam.getT();
                            if(t instanceof CssBag){
                                CssBag cssBag=(CssBag)t;
                                cssBag.getMap().put("position",modelParam.getCurModelValue().toString());
                            }
                        }
                    })
                    .invoke();
        }else if("positionLine".equalsIgnoreCase(name)){
            nfaStateMachine.getEndNode().setHandle(new RelevanceHandle() {
                public void handle(ModelParam modelParam) {
                    System.out.println("-----------成功");
                }
            });

        }
        return new InvokerImpl(value)
                .setIsPrint(true)
                .setHandleName(name)
                .relevance(new RelevanceHandle(){
                    public void handle(ModelParam modelParam) {

                    }
                })
                .invoke();

    }
}
