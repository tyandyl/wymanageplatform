package com.wy.manage.platform.core.parser;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by tianye
 */
public class HandleInfo {
    private RelevanceHandle relevanceHandle;
    private String handleName;
    private int curChar;
    private Set<String> stateNum=new TreeSet<String>();

    public HandleInfo(RelevanceHandle relevanceHandle,int curChar,String stateNum,String handleName){
        this.relevanceHandle=relevanceHandle;
        this.curChar=curChar;
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

    public int getCurChar() {
        return curChar;
    }

    public void setCurChar(int curChar) {
        this.curChar = curChar;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }
}
