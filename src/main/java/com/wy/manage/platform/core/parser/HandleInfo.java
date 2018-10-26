package com.wy.manage.platform.core.parser;

import java.util.List;

/**
 * Created by tianye13 on 2018/10/26.
 */
public class HandleInfo {
    private RelevanceHandle relevanceHandle;
    private int curChar;
    private String stateNum;

    public HandleInfo(RelevanceHandle relevanceHandle,int curChar,String stateNum){
        this.relevanceHandle=relevanceHandle;
        this.curChar=curChar;
        this.stateNum=stateNum;
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
}
