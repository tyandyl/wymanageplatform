package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Set;

/**
 * Created by tianye
 * 当前类记录经过的dfa状态
 */
public class NfaStateNodeRecord {
    //dnf状态码集合
    private Set<String> dfaStateNum;
    //列字符
    private int cRecord=-1;

    public NfaStateNodeRecord(Set<String> dfaStateNum,int cRecord){
        this.dfaStateNum=dfaStateNum;
        this.cRecord=cRecord;
    }

    public Set<String> getDfaStateNum() {
        return dfaStateNum;
    }

    public void setDfaStateNum(Set<String> dfaStateNum) {
        this.dfaStateNum = dfaStateNum;
    }

    public int getcRecord() {
        return cRecord;
    }

    public void setcRecord(int cRecord) {
        this.cRecord = cRecord;
    }
}
