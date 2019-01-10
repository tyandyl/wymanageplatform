package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.*;

/**
 * Created by tianye
 */
public class AnalyzeStateMoveHandle implements StateMoveHandle<Set<String>,List<String>,List<NfaStateNode>,DfaContext>{

    public void analyze(Set<String> strings, List<String> strings2, Integer var, List<NfaStateNode> nfaStateNodes, DfaContext dfaContext) throws Exception {
        //我们认为经过字符后指向的状态，是对该字符的总结，所以开始节点的状态集合，不可能执行，因为没有字符
        //我们设置规则如下：获取开始节点的ε-closure(s)表示由状态s经由条件ε可以到达的所有状态的集合
        //查询状态行表，根据当前var输入，获取下一个状态，将下一个有动作的状态放入执行列表
        //获取action不为空的动作，排序
        List<String> nodeStateNumList=new ArrayList<String>();
        for(String str:strings){
            //获取列数
            Map<Integer, List<String>> integerListMap =dfaContext.getMap().get(str);
            if(integerListMap!=null){
                //获取var输入的下一个状态
                List<String> list1 = integerListMap.get(var);
                if(list1!=null && list1.size()>0){
                    for(String str1:list1){
                        if(!strings2.contains(str1)){
                            Set<String> listAdd = dfaContext.getMapEmpty().get(str1);

                            if(listAdd==null ||listAdd.size()==0){
                                ExceptionTools.ThrowException("至少包含他自己");
                            }
                            for(String str3:listAdd){
                                if(!strings2.contains(str3)){
                                    strings2.add(str3);
                                    NfaStateNode nfaStateNode = dfaContext.getMapState().get(str3);
                                    if(nfaStateNode==null){
                                        System.out.println("报错了,没有nfa状态");
                                        break;
                                    }
                                    //将新的状态放入集合列表
                                    if(nfaStateNode.getAction()!=null){
                                        if(!nodeStateNumList.contains(nfaStateNode.getStateNum())){
                                            nodeStateNumList.add(nfaStateNode.getStateNum());
                                            nfaStateNodes.add(nfaStateNode);
                                        }

                                    }
                                }
                            }

                        }
                    }
                }
            }

        }
    }

    public void execute(int curInt,
                                  int maxInt,
                                  Set<String> startNode,
                                  ModelParam modelParam,
                                  DfaContext dfaContext,
                                  StateMoveHandle stateMoveHandle)throws Exception{
        //新的状态集合
        List<String> listNew=new ArrayList<String>();
        //需要action的状态
        List<NfaStateNode> nodes=new ArrayList<NfaStateNode>();

        while (curInt<maxInt){
            analyze(startNode,listNew,Integer.valueOf(modelParam.getChars()[modelParam.getCurInt()]),  nodes,dfaContext);
            if(listNew.size()==0){
                System.out.println("当前字符不符合规则,当前字符是:"+modelParam.getChars()[modelParam.getCurInt()]
                        +",其积累的字符串是:"+modelParam.getCurModelValue());
                System.out.println("完毕");
                break;
            }

            Set<String> listNewEx=new HashSet<String>();
            for(String y:listNew){
                listNewEx.add(y);
            }

            modelParam.recordCurModelValue(modelParam.getChars()[modelParam.getCurInt()]);

            modelParam.addCurInt();

            startNode=listNewEx;
            nodes.clear();
            listNew.clear();
        }
    }

}
