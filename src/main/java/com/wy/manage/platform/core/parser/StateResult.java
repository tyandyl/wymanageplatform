package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tianye
 */
public class StateResult {
    //存放最新状态集合
    private Set<String> list=new HashSet<String>();
    //存放action不为空的状态表
    private List<NfaStateNode> nodes=new ArrayList<NfaStateNode>();
    //排除node以及node连接的状态的集合
    private Set<String> listL=new HashSet<String>();

    private Set<String> nodeStateNumList=new HashSet<String>();

    public Set<String> getList() {
        return list;
    }

    public void setList(Set<String> list) {
        this.list = list;
    }

    public List<NfaStateNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<NfaStateNode> nodes) {
        this.nodes = nodes;
    }

    public Set<String> getListL() {
        return listL;
    }

    public void setListL(Set<String> listL) {
        this.listL = listL;
    }

    public Set<String> getNodeStateNumList() {
        return nodeStateNumList;
    }

    public void setNodeStateNumList(Set<String> nodeStateNumList) {
        this.nodeStateNumList = nodeStateNumList;
    }

    public void handle(DfaContext dfaContext){
        if(nodes.size()>0){
            Set<String> all=new HashSet<String>();
            for(NfaStateNode node:nodes){
                Set<String> listAdd = dfaContext.getMapEmpty().get(node.getStateNum());
                if(listAdd!=null && listAdd.size()>0){
                    for(String str:listAdd){
                        all.add(str);
                    }
                }
            }
            if(list.size()>0){
                for(String stm:list){
                    if(!all.contains(stm)){
                        listL.add(stm);
                    }
                }
            }

        }

    }

    public void clear(){
        list.clear();
        nodes.clear();
        listL.clear();
        nodeStateNumList.clear();
    }
}
