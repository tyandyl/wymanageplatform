package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.*;

/**
 * Created by tianye
 */
public class AnalyzeStateMoveHandle implements StateMoveHandle<Set<String>,StateResult,DfaContext>{

    public void analyze(Set<String> strings,StateResult result, Integer var, DfaContext dfaContext) throws Exception {
        //我们认为经过字符后指向的状态，是对该字符的总结，所以开始节点的状态集合，不可能执行，因为没有字符
        //我们设置规则如下：获取开始节点的ε-closure(s)表示由状态s经由条件ε可以到达的所有状态的集合
        //查询状态行表，根据当前var输入，获取下一个状态，将下一个有动作的状态放入执行列表
        //获取action不为空的动作，排序
        Set<String> nodeStateNumList=new HashSet<String>();
        for(String str:strings){
            //获取列数
            Map<Integer, List<String>> integerListMap =dfaContext.getMap().get(str);
            if(integerListMap!=null){
                //获取var输入的下一个状态
                List<String> list1 = integerListMap.get(var);
                if(list1!=null && list1.size()>0){
                    for(String str1:list1){
                        if(!result.getList().contains(str1)){
                            Set<String> listAdd = dfaContext.getMapEmpty().get(str1);

                            if(listAdd==null ||listAdd.size()==0){
                                ExceptionTools.ThrowException("至少包含他自己");
                            }
                            for(String str3:listAdd){
                                if(!result.getList().contains(str3)){
                                    result.getList().add(str3);
                                    NfaStateNode nfaStateNode = dfaContext.getMapState().get(str3);
                                    if(nfaStateNode==null){
                                        System.out.println("报错了,没有nfa状态");
                                        break;
                                    }
                                    //将新的状态放入集合列表
                                    if(nfaStateNode.getAction()!=null){
                                        if(!nodeStateNumList.contains(nfaStateNode.getStateNum())){
                                            nodeStateNumList.add(nfaStateNode.getStateNum());
                                            result.getNodes().add(nfaStateNode);
                                        }

                                    }
                                }
                            }

                        }
                    }
                }
            }

        }
        result.setNodeStateNumList(nodeStateNumList);
        result.handle(dfaContext);
    }


}
