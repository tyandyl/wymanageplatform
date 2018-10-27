package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by tianye
 */
public class DfaContext {
    private NfaStateMachine parser=null;
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
//    private Map<String,List<String>> mapDfa=new HashMap<String, List<String>>();

    //key是状态，value表示由状态s经由条件ε可以到达的所有状态的集合
    private Map<String,Set<String>> mapEmpty=new HashMap<String, Set<String>>();

    //存放标记的状态码
    private String markStateNum;
    private List<String> markMapKeys=new ArrayList<String>();
    private Set<String> markMapEmptyKeys=new TreeSet<String>();

    //ε-closure(s)表示由状态s经由条件ε可以到达的所有状态的集合
    public DfaContext buildEmptyStateGather(){
        for(Map.Entry<String,Map<Integer,List<String>>> info:map.entrySet()){
            StringBuffer buffer=new StringBuffer("该状态是:"+info.getKey());
            buffer.append("\n");
            for(Map.Entry<Integer,List<String>> v:info.getValue().entrySet()){
                StringBuffer buf=new StringBuffer();
                if(v!=null){
                    buf.append("输入的字符是:"+v.getKey()+",下一个状态是:");
                    List<String> value = v.getValue();
                    if(value!=null && value.size()>0){
                        for(String str:value){
                            NfaStateNode nfaStateNode = mapState.get(str);
                            if(nfaStateNode!=null && StringUtils.isNotBlank(nfaStateNode.getHandleName())){
                                buf.append(str+",其事件是:"+nfaStateNode.getHandleName()+",");
                            }else {
                                buf.append(str+",");
                            }

                        }
                    }
                    buf.append("\n");
                }

                buffer.append(buf);
            }
            System.out.println(buffer);

            new StateMoveHandle<String,Map<String,Set<String>>>(){

                public void move(String s, Map<String, Set<String>> mapEmpty, Integer var) {
                    //98页
                    Stack<String> stack=new Stack<String>();
                    Set<String> list = mapEmpty.get(s);
                    if(list==null){
                        list=new TreeSet<String>();
                        list.add(s);
                        mapEmpty.put(s,list);
                        stack.push(s);
                        if(s.equalsIgnoreCase(markStateNum)){
                            markMapEmptyKeys.add(s);
                        }
                    }
                    while (!stack.empty()){
                        String pop = stack.pop();
                        Map<Integer, List<String>> integerListMap = map.get(pop);
                        if(integerListMap!=null){
                            List<String> list1 = integerListMap.get(130);
                            if(list1!=null && list1.size()>0){
                                for(String str:list1){
                                    if(!list.contains(str)){
                                        list.add(str);
                                        if(str.equalsIgnoreCase(markStateNum)){
                                            markMapEmptyKeys.add(s);
                                        }
                                        stack.push(str);
                                    }
                                }
                            }
                        }
                    }



                }
            }.move(info.getKey(),mapEmpty,130);
        }
        for(String str:markMapEmptyKeys){
            System.out.println("该状态码已经放到空集合的:"+str+" key中");
        }
        return this;
    }

    public Set<String> getMarkMapEmptyKeys() {
        return markMapEmptyKeys;
    }

    public void setMarkMapEmptyKeys(Set<String> markMapEmptyKeys) {
        this.markMapEmptyKeys = markMapEmptyKeys;
    }

    public Map<String, Set<String>> getMapEmpty() {
        return mapEmpty;
    }

    public void setMapEmpty(Map<String, Set<String>> mapEmpty) {
        this.mapEmpty = mapEmpty;
    }

    public void putMapState(String str, NfaStateNode nfaStateNode){
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
            list=new ArrayList<String>();
            list.add(nextState);
        }
        integerListMap.put(input,list);
    }

    public void addInputParam(Integer param){
        if(!inputParams.contains(param)){
            inputParams.add(param);
        }
    }

    public NfaStateMachine getParser() {
        return parser;
    }

    public DfaContext setParser(NfaStateMachine parser) {
        this.parser = parser;
        return this;
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

    public String getMarkStateNum() {
        return markStateNum;
    }

    public void setMarkStateNum(String markStateNum) {
        this.markStateNum = markStateNum;
    }

    public List<String> getMarkMapKeys() {
        return markMapKeys;
    }

    public void setMarkMapKeys(List<String> markMapKeys) {
        this.markMapKeys = markMapKeys;
    }
}
