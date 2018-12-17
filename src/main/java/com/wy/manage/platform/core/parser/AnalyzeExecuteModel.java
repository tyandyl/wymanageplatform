package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.action.Action;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.StyleSheetType;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by tianye
 */
public class AnalyzeExecuteModel {

    private static Character[] specChars={32,10,13};

    public static void execute(ModelParam modelParam,NfaStateMachine parser) throws Exception {

//        char[] chars = str.toCharArray();
//        ModelParam<CssBag> modelParam = new ModelParam<CssBag>(cssBag,chars);
        DfaContext context = modelParam.initDfaContext(parser).buildEmptyStateGather();
        execute(modelParam,context);
    }

    public static void execute(final ModelParam modelParam, final DfaContext context)throws Exception{
        Set<String> list =context.getMapEmpty().get(context.getStartNodeStateNum());
        while (modelParam.getCurInt()<modelParam.getChars().length){
            //System.out.println("输入字符是:"+modelParam.getChars()[modelParam.getCurInt()]);
            //新的状态集合,99页
            List<String> listNew=new ArrayList<String>();
            new StateMoveHandle<Set<String>,List<String>>(){
                public void move(Set<String> strings, List<String> strings2, Integer var) {
                    List<NfaStateNode> nodes=new ArrayList<NfaStateNode>();
                    //遍历ε-closure(s)表示由状态s经由条件ε可以到达的所有状态的集合
                    for(String str:strings){
                        NfaStateNode nfaStateNode = context.getMapState().get(str);
                        if(nfaStateNode==null){
                            System.out.println("报错了,没有nfa状态");
                            break;
                        }
                        if(nfaStateNode.getAction()!=null){
                            nodes.add(nfaStateNode);

                        }
                        //获取列数
                        Map<Integer, List<String>> integerListMap =context.getMap().get(str);
                        if(integerListMap!=null){
                            //获取var输入的下一个状态
                            List<String> list1 = integerListMap.get(var);
                            if(list1!=null && list1.size()>0){
                                for(String str1:list1){
                                    if(!strings2.contains(str1)){
                                        strings2.add(str1);
                                    }
                                }
                            }
                        }
                    }
                    Collections.sort(nodes, new Comparator<NfaStateNode>() {
                        public int compare(NfaStateNode o1, NfaStateNode o2) {
                            int priority1 = o1.getPriority();
                            int priority2 = o2.getPriority();
                            if(priority1>priority2){
                                return -1;
                            }else if(priority1==priority2){
                                return 0;
                            }else {
                                return 0;
                            }
                        }
                    });
                    for(NfaStateNode nfaStateNode:nodes){
                        if(nfaStateNode.getAction().getName().equalsIgnoreCase("backgrounds")){
                            System.out.println();
                        }
                        nfaStateNode.getAction().action(modelParam);
                        //优先级低的执行完了需要锁死
                        if(nfaStateNode.getAction().getPriority()>1){
                            modelParam.getLockRegularName().add(nfaStateNode.getAction().getName());
                        }
                        //最高优先度执行完毕后需要清空，避免两个状态机衔接问题，一个没执行完，另一个就填充
                        if(nfaStateNode.getAction().getPriority()==1){
                            modelParam.getRegularValue().clear();
                            modelParam.getLockRegularName().clear();
                        }

                        if(nfaStateNode.getAction().getIntraGroupNames()!=null
                                && nfaStateNode.getAction().getIntraGroupNames().size()>0
                                && nfaStateNode.getAction().getPriority()>1){
                            for(String actionName:nfaStateNode.getAction().getIntraGroupNames()){
                                modelParam.getRegularValue().remove(actionName);
                                modelParam.getLockRegularName().remove(actionName);
                            }

                        }
                    }

                }

            }.move(list,listNew,Integer.valueOf(modelParam.getChars()[modelParam.getCurInt()]));
            for(String str:list){
                NfaStateNode nfaStateNode = context.getMapState().get(str);
                if(nfaStateNode==null){
                    System.out.println("报错了,没有nfa状态");
                    break;
                }
                List<String> belongRegulars = nfaStateNode.getBelongRegular();
                if(belongRegulars!=null && belongRegulars.size()>0){
                    for(String belongRegular:belongRegulars){
                        if(StringUtils.isNotBlank(belongRegular)){
                            Map<String, StringBuffer> regularValue = modelParam.getRegularValue();
                            StringBuffer buffer = regularValue.get(belongRegular);
                            int i = modelParam.getCurInt();
                            if(buffer==null){
                                if(modelParam.addRegularNum(belongRegular, i)){
                                    StringBuffer buffer1=new StringBuffer();
                                    buffer1.append(modelParam.getChars()[i]);
                                    regularValue.put(belongRegular,buffer1);
                                }
                            }else if(!modelParam.getLockRegularName().contains(belongRegular)){
                                if(modelParam.addRegularNum(belongRegular, i)){
                                    buffer.append(modelParam.getChars()[i]);
                                    regularValue.put(belongRegular,buffer);
                                }

                            }
                        }
                    }
                }
            }
            if(listNew.size()==0){
                System.out.println("当前字符不符合规则,当前字符是:"+modelParam.getChars()[modelParam.getCurInt()]
                        +",其积累的字符串是:"+modelParam.getCurModelValue());
                System.out.println("完毕");
                break;
            }

            Set<String> listMost=new TreeSet<String>();
            for(String str:listNew){
                listMost.add(str);
                Set<String> list1 = context.getMapEmpty().get(str);

                if(list1==null ||list1.size()==0){
                    ExceptionTools.ThrowException("至少包含他自己");
                }
                for(String str2:list1){
                    listMost.add(str2);
                }
            }
            modelParam.recordCurModelValue(modelParam.getChars()[modelParam.getCurInt()]);

            modelParam.addCurInt();

            list=listMost;
        }
        Object t = modelParam.getT();
        if(t instanceof Page) {
            Page page = (Page) t;
            //System.out.println(page.getStr());
        }

        return;
    }
}
