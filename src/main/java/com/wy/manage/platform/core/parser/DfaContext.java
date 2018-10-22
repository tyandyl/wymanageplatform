package com.wy.manage.platform.core.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by tianye
 */
public class DfaContext {
    //状态和路径的二维图
    private Map<String,Map<Integer,List<String>>> map=new HashMap<String, Map<Integer, List<String>>>();

    //nfa的状态码和节点的对应关系
    private Map<String,NfaStateNode> mapState=new HashMap<String,NfaStateNode>();

    //开始节点的状态码
    private String startNodeStateNum;

    public void init(){
        Stack<String> oldState=new Stack<String>();
    }

    public Map<String, NfaStateNode> getMapState() {
        return mapState;
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
