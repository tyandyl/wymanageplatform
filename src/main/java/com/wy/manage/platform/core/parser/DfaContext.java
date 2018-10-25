package com.wy.manage.platform.core.parser;

import java.util.*;

/**
 * Created by tianye
 */
public class DfaContext {
    //状态和路径的二维图
    //key是行数，代表状态
    //Value第一位是输入，这里使用整形0-127代表单个字符，130代表空，135代表字符集(字符集进一步处理，化为单个列)
    //Value第二位是下一个状态
    private Map<String,Map<Integer,List<String>>> map=new HashMap<String, Map<Integer, List<String>>>();

    //nfa的状态码和节点的对应关系
    private Map<String,NfaStateNode> mapState=new HashMap<String,NfaStateNode>();

    //开始节点的状态码
    private String startNodeStateNum;

    //结束节点的状态码
    private String endNodeStateNum;

    //输入的参数，130代表空ε
    private List<Integer> inputParams=new ArrayList<Integer>();

    //每个Dfa挂几个NFA节点
    private Map<String,List<String>> mapDfa=new HashMap<String, List<String>>();

    //key是状态，value表示由状态s经由条件ε可以到达的所有状态的集合
    private Map<String,List<String>> mapEmpty=new HashMap<String, List<String>>();

    //ε-closure(s)表示由状态s经由条件ε可以到达的所有状态的集合
    public DfaContext BuildStateGather(){
        for(Map.Entry<String,Map<Integer,List<String>>> info:map.entrySet()){
            new StateMoveHandle<String,Map<String,List<String>>>(){
                /**
                 *
                 * @param s 状态
                 * @param mapEmpty 结果集，存放寻找的结果
                 * @param var 条件ε，由哪条 条线寻找
                 */
                public void move(String s, Map<String, List<String>> mapEmpty, Integer var) {
                    //寻找当前状态下的列
                    Map<Integer, List<String>> integerListMap = map.get(s);
                    if(integerListMap!=null && integerListMap.size()>0){
                        //寻找下一个状态
                        List<String> list = integerListMap.get(var);
                        if(list!=null && list.size()>0){
                            for(String str:list){
                                List<String> list1 = mapEmpty.get(s);
                                if(list1==null){
                                    list1=new ArrayList<String>();
                                    mapEmpty.put(s,list1);
                                }
                                if(!list1.contains(str)){
                                    list1.add(str);
                                    //说明由状态s经由条件ε可以到达的所有状态的集合还没有找过
                                    if(mapEmpty.get(str)==null){
                                        move(str,mapEmpty,130);
                                    }
                                }
                            }
                        }
                    }

                }
            }.move(info.getKey(),mapEmpty,130);
        }
        return this;
    }

    public void putMapState(String str,NfaStateNode nfaStateNode){
        mapState.put(str,nfaStateNode);
    }

    /**
     * 根据当前输入，增加下一个状态列表
     * @param integerListMap 列表
     * @param input 当前输入
     * @param nextState 下一个状态
     */
    public void handleMapCol(Map<Integer, List<String>> integerListMap,Integer input,String nextState){
        //获取空列的下一个状态
        List<String> list = integerListMap.get(input);
        //如果下一个状态列表有值，则直接填充
        if(list!=null){
            if(!list.contains(nextState)){
                list.add(nextState);
            }
        }else {
            List<String> listNew=new ArrayList<String>();
            listNew.add(nextState);
            integerListMap.put(input,listNew);
        }
    }

    public void addInputParam(Integer param){
        if(!inputParams.contains(param)){
            inputParams.add(param);
        }
    }

    public Map<String, NfaStateNode> getMapState() {
        return mapState;
    }

    public List<Integer> getInputParams() {
        return inputParams;
    }

    public void setInputParams(List<Integer> inputParams) {
        this.inputParams = inputParams;
    }

    public Map<String, List<String>> getMapDfa() {
        return mapDfa;
    }

    public void setMapDfa(Map<String, List<String>> mapDfa) {
        this.mapDfa = mapDfa;
    }

    public String getEndNodeStateNum() {
        return endNodeStateNum;
    }

    public void setEndNodeStateNum(String endNodeStateNum) {
        this.endNodeStateNum = endNodeStateNum;
    }

    public void setMapState(Map<String, NfaStateNode> mapState) {
        this.mapState = mapState;
    }

    public Map<String, Map<Integer, List<String>>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<Integer, List<String>>> map) {
        this.map = map;
    }

    public String getStartNodeStateNum() {
        return startNodeStateNum;
    }

    public void setStartNodeStateNum(String startNodeStateNum) {
        this.startNodeStateNum = startNodeStateNum;
    }
}
