package com.wy.manage.platform.core.parser;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by tianye
 */
public class HandleInfo {
    private RelevanceHandle relevanceHandle;
    private String handleName;
    private int curCharInt;
    private Set<String> stateNum=new TreeSet<String>();

    public HandleInfo(RelevanceHandle relevanceHandle,int curCharInt,String stateNum,String handleName){
        this.relevanceHandle=relevanceHandle;
        this.curCharInt=curCharInt;
        this.stateNum.add(stateNum);
        this.handleName=handleName;
    }

    public void addSet(String stateNum){
        this.stateNum.add(stateNum);
    }

    public RelevanceHandle getRelevanceHandle() {
        return relevanceHandle;
    }

    public void setRelevanceHandle(RelevanceHandle relevanceHandle) {
        this.relevanceHandle = relevanceHandle;
    }

    public int getCurCharInt() {
        return curCharInt;
    }

    public void setCurCharInt(int curCharInt) {
        this.curCharInt = curCharInt;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }
}
