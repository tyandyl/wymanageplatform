package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.action.Action;
import com.wy.manage.platform.core.utils.ExceptionTools;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by tianye
 */
public class AnalyzeExecuteModel {

    private static Character[] specChars={32,10,13};

    public static void execute(String str,CssBag cssBag,NfaStateMachine parser) throws Exception {

        char[] chars = str.toCharArray();
        ModelParam<CssBag> modelParam = new ModelParam<CssBag>(cssBag,chars);
        DfaContext context = modelParam.initDfaContext(parser).buildEmptyStateGather();
        execute(modelParam,context);
    }

    public static void execute(final ModelParam modelParam, final DfaContext context)throws Exception{
        Set<String> list =context.getMapEmpty().get(context.getStartNodeStateNum());
        while (modelParam.getCurInt()<modelParam.getChars().length){
            //新的状态集合,99页
            List<String> listNew=new ArrayList<String>();
            new StateMoveHandle<Set<String>,List<String>>(){
                public void move(Set<String> strings, List<String> strings2, Integer var) {
                    //遍历ε-closure(s)表示由状态s经由条件ε可以到达的所有状态的集合
                    for(String str:strings){
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
                }
            }.move(list,listNew,Integer.valueOf(modelParam.getChars()[modelParam.getCurInt()]));

            if(listNew.size()==0){
                System.out.println("报错了,没有dfa状态集合值");
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
            System.out.println("当前输入字符是:"+modelParam.getChars()[modelParam.getCurInt()]);
            List<Action> actionList=new ArrayList<Action>();
            for(String str:listMost){
                NfaStateNode nfaStateNode = context.getMapState().get(str);
                if(nfaStateNode==null){
                    System.out.println("报错了,没有nfa状态");
                    break;
                }
                if(nfaStateNode.getAction()!=null){
                    System.out.println("当前动作是:"+nfaStateNode.getAction().getName());
                    actionList.add(nfaStateNode.getAction());
                    nfaStateNode.getAction().action(modelParam);
                    modelParam.clearCurModelValue();
                }
            }
            if(actionList.size()>1){
                System.out.println("有两个action，需要设置优先级");
            }
            modelParam.addCurInt();
            list=listMost;
        }
       // handleEvent( modelParam);
        return;
    }
}
