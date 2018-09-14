package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye on 2018/9/8.
 */
public class XContentItem {
    private int legend;
    private int index;
    private MeanType meanType;
    private NfaStateMachine nfaStateMachine=null;
    private boolean isOr=false;

    public XContentItem(int legend,int index){
        this.legend=legend;
        this.index=index;
    }

    public XContentItem(int legend,int index,MeanType meanType){
        this.legend=legend;
        this.index=index;
        this.meanType=meanType;
    }

    public MeanType getMeanType() {
        return meanType;
    }

    public void setMeanType(MeanType meanType) {
        this.meanType = meanType;
    }

    public XContentItem(NfaStateMachine nfaStateMachine){
        this.nfaStateMachine=nfaStateMachine;
    }

    public int getLegend() {
        return legend;
    }

    public void setLegend(int legend) {
        this.legend = legend;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public NfaStateMachine getNfaStateMachine() {
        return nfaStateMachine;
    }

    public void setNfaStateMachine(NfaStateMachine nfaStateMachine) {
        this.nfaStateMachine = nfaStateMachine;
    }

    public boolean isOr() {
        return isOr;
    }

    public void setOr(boolean or) {
        isOr = or;
    }
}
