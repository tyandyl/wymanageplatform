package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.AtomicTools;
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
        //记录非原子性正则次数
        Map<String,Integer> mapInteger=new HashMap<String, Integer>();
        //记录原子性正则
        Map<String,String> mapName=new HashMap<String, String>();
        NfaStateMachine nfaStateMachine=null;
        for (String str:strings) {
            nfaStateMachine=null;
            String name =str;
            String value = properties.getProperty(str);
            if (value != null) {
                if(name.equalsIgnoreCase("attributeAll")){
                    System.out.println("attributeAll");
                }
                if(value.contains(" ")){
                    String[] split = value.split(" ");
                    if(split!=null && split.length!=0){
                        //记录当前Value有几个重复的，比如attributeFirstLine=ignore attributeTag attributeName attributeFirstOpenCurly
                        //ignore 重复必须重新创建第一个ignore节点
                        boolean isOr=false;
                        for(int i=0;i<split.length;i++){
                            if(split[i].equalsIgnoreCase("|")){
                                isOr=true;
                                continue;
                            }
                            NfaStateMachine nfaStateMachine1 = map.get(split[i]);

                            if(isOr){
                                if(nfaStateMachine1!=null){
                                    Integer integer = mapInteger.get(split[i]);
                                    if(integer==0){
                                        nfaStateMachine = or(nfaStateMachine,nfaStateMachine1);
                                    }else {
                                        nfaStateMachine = or(nfaStateMachine, NfaManager.deepClone(nfaStateMachine1));
                                    }
                                    mapInteger.put(split[i],(integer+1));
                                }else {
                                    String valueSingle = mapName.get(split[i]);
                                    //当存在的时候，使用动作解析
                                    if(StringUtils.isNotBlank(valueSingle)){
                                        NfaStateMachine nfaStateMachine2 = getNfaStateMachine(split[i], valueSingle, null);
                                        nfaStateMachine=or(nfaStateMachine,nfaStateMachine2);
                                    }else {
                                        //说明该正则位于产生式中，且没有动作
                                        NfaStateMachine nfaStateMachine2 = getNfaStateMachine(split[i], split[i], null);
                                        nfaStateMachine=or(nfaStateMachine,nfaStateMachine2);
                                    }
                                }
                                isOr=false;
                            }else {
                                if(nfaStateMachine1!=null){
                                    Integer integer = mapInteger.get(split[i]);
                                    if(integer==0){
                                        nfaStateMachine = link(nfaStateMachine,nfaStateMachine1);
                                    }else {
                                        nfaStateMachine = link(nfaStateMachine, NfaManager.deepClone(nfaStateMachine1));
                                    }
                                    mapInteger.put(split[i],(integer+1));
                                }else {
                                    String valueSingle = mapName.get(split[i]);
                                    //当存在的时候，使用动作解析
                                    if(StringUtils.isNotBlank(valueSingle)){
                                        NfaStateMachine nfaStateMachine2 = getNfaStateMachine(split[i], valueSingle, null);
                                        nfaStateMachine=link(nfaStateMachine,nfaStateMachine2);
                                    }else {
                                        //说明该正则位于产生式中，且没有动作
                                        NfaStateMachine nfaStateMachine2 = getNfaStateMachine(split[i], split[i], null);
                                        nfaStateMachine=link(nfaStateMachine,nfaStateMachine2);
                                    }
                                }
                            }


                        }
                        map.put(name,getNfaStateMachine(name, value, nfaStateMachine));
                        mapInteger.put(name,0);
                    }
                }else {
                    mapName.put(name,value);
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
        final StringBuffer str=new StringBuffer(link1.getStartNode().getHandleName()
                +" "+link2.getStartNode().getHandleName());
        NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(link1, link2);
        final Integer num = AtomicTools.getBiUniqueInteger();
        System.out.println("状态机:"+str+"的统一标识符是:"+num);
        NfaManager.traverse(linkNfaStateMachine.getStartNode(),new NodeHandle<NfaStateNode>(){
            public void handle(NfaStateNode o) throws Exception {
                o.setHandleName(str.toString());
                if(o.getObjectId()!=num){
                    o.setObjectId(num);
                }
            }
        }, num);
        return linkNfaStateMachine;
    }

    public static NfaStateMachine or(NfaStateMachine link1,NfaStateMachine link2)throws Exception{
        if(link1==null){
            return link2;
        }
        if(link2==null){
            return link1;
        }
        final StringBuffer str=new StringBuffer(link1.getStartNode().getHandleName()
                +" "+link2.getStartNode().getHandleName());

        NfaStateMachine orNfaStateMachine = NfaManager.createOrNfaStateMachine(link1, link2);
        final Integer num = AtomicTools.getBiUniqueInteger();
        System.out.println("状态机:"+str+"的统一标识符是:"+num);
        NfaManager.traverse(orNfaStateMachine.getStartNode(),new NodeHandle<NfaStateNode>(){
            public void handle(NfaStateNode o) throws Exception {
                o.setHandleName(str.toString());
                if(o.getObjectId()!=num){
                    o.setObjectId(num);
                }
            }
        }, num);
        return orNfaStateMachine;
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
                    .invoke();
        }else if("positionLine".equalsIgnoreCase(name)
                ||"displayLine".equalsIgnoreCase(name)){
            nfaStateMachine.getEndNode().setHandle(new RelevanceHandle(){
                public void handle(ModelParam modelParam) {
                    Object t = modelParam.getT();
                    if(t instanceof CssBag){
                        CssBag cssBag=(CssBag)t;
                        String trim = modelParam.getCurModelValue().toString().trim();
                        String[] split = trim.split(":");
                        cssBag.getMap().put(split[0],split[1]);
                    }
                }
            });
        }else if("attributeFirstLine".equalsIgnoreCase(name)){
            nfaStateMachine.getEndNode().setHandle(new RelevanceHandle(){
                public void handle(ModelParam modelParam) {

                }
            });
        }
        if(nfaStateMachine!=null){
            return nfaStateMachine;
        }
        return new InvokerImpl(value)
                .setIsPrint(true)
                .setHandleName(name)
                .invoke();
    }
}
