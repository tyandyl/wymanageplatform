package com.wy.manage.platform.core.parser;

import java.util.List;

/**
 * Created by tianye
 * 当前类记录经过的dfa状态
 */
public class NfaStateNodeRecord {
    //dnf状态码集合
    private List<String> dfaStateNum;
    //列字符
    private int cRecord=-1;

    public NfaStateNodeRecord(List<String> dfaStateNum,int cRecord){
        this.dfaStateNum=dfaStateNum;
        this.cRecord=cRecord;
    }

    public List<String> getDfaStateNum() {
        return dfaStateNum;
    }

    public void setDfaStateNum(List<String> dfaStateNum) {
        this.dfaStateNum = dfaStateNum;
    }

    public int getcRecord() {
        return cRecord;
    }

    public void setcRecord(int cRecord) {
        this.cRecord = cRecord;
    }
}
