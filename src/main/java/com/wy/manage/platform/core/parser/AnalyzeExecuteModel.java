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

        DfaContext context = modelParam.initDfaContext(parser).buildEmptyStateGather();
        execute(modelParam,context);
    }

    public static void execute(final ModelParam modelParam, final DfaContext context)throws Exception{
        //获取开始节点
        Set<String> list =context.getMapEmpty().get(context.getStartNodeStateNum());
        while (modelParam.getCurInt()<modelParam.getChars().length){
            if(modelParam.getCurInt()==283){
                System.out.println("输入字符是:"+modelParam.getChars()[modelParam.getCurInt()]);
            }
            //新的状态集合
            List<String> listNew=new ArrayList<String>();
            //需要action的状态
            List<NfaStateNode> nodes=new ArrayList<NfaStateNode>();
            new AnalyzeStateMoveHandle(){}.move(list,listNew,Integer.valueOf(modelParam.getChars()[modelParam.getCurInt()]),  nodes,context);

            if(listNew.size()==0){
                System.out.println("当前字符不符合规则,当前字符是:"+modelParam.getChars()[modelParam.getCurInt()]
                        +",其积累的字符串是:"+modelParam.getCurModelValue());
                System.out.println("完毕");
                break;
            }

            Set<String> listNewEx=new HashSet<String>();
            for(String y:listNew){
                listNewEx.add(y);
                if(nodes.size()==0){
                    //获取新的状态
                    NfaStateNode nfaStateNode = context.getMapState().get(y);
                    //判断下一个状态属于哪个正则
                    List<String> belongRegulars = nfaStateNode.getBelongRegular();
                    if(belongRegulars!=null && belongRegulars.size()>0){
                        for(String belongRegular:belongRegulars){
                            if(StringUtils.isNotBlank(belongRegular)){
                                Map<String, StringBuffer> regularValue = modelParam.getRegularValue();
                                StringBuffer buffer = regularValue.get(belongRegular);
                                //对当前字符进行总结
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

            }

            if(nodes.size()>0){
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
                    boolean isSc=false;
                    //往后预测20位
                    int curIntL = modelParam.getCurInt()+1;
                    int guessInt=curIntL+20;
                    Set<String> listGuess = context.getMapEmpty().get(nfaStateNode.getStateNum());









                    nfaStateNode.getAction().action(modelParam);
                    String value = nfaStateNode.getAction().getValue();
                    char c=0;
                    if(StringUtils.isNotBlank(value)){
                        String replace = value.replace(")", "");
                        c = replace.toCharArray()[replace.length() - 1];
                    }
                    //优先级低的执行完了需要锁死
                    if(nfaStateNode.getAction().getPriority()>1
                            && c!='+'){
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




            modelParam.recordCurModelValue(modelParam.getChars()[modelParam.getCurInt()]);

            modelParam.addCurInt();

            list=listNewEx;
            nodes.clear();
        }
        Object t = modelParam.getT();
        if(t instanceof Page) {
            Page page = (Page) t;
            //System.out.println(page.getStr());
        }

        return;
    }
}
