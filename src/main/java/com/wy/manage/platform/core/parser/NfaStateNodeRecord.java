package com.wy.manage.platform.core.parser;

/**
 * Created by tianye
 */
public class NfaStateNodeRecord {
    private NfaStateNode nfaStateNode;
    //默认值为-2，-1为空集
    private int cRecord=-2;

    public NfaStateNodeRecord(NfaStateNode nfaStateNode,int cRecord){
        this.nfaStateNode=nfaStateNode;
        this.cRecord=cRecord;
    }

    public NfaStateNode getNfaStateNode() {
        return nfaStateNode;
    }

    public void setNfaStateNode(NfaStateNode nfaStateNode) {
        this.nfaStateNode = nfaStateNode;
    }

    public int getcRecord() {
        return cRecord;
    }

    public void setcRecord(int cRecord) {
        this.cRecord = cRecord;
    }
}
