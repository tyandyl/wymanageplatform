package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tianye on 2018/9/8.
 */
public class XContentItem {
    private int legend;
    //index融合，数目较大
    private Set<Integer> index =new HashSet<Integer>();
    private MeanType meanType;
    private NfaStateMachine nfaStateMachine=null;
    private boolean isOr=false;

    public XContentItem(int legend,int index){
        this.legend=legend;
        this.index.add(index);
    }

    public XContentItem(int legend,int index,MeanType meanType){
        this.legend=legend;
        this.index.add(index);
        this.meanType=meanType;
    }

    public void addIndex(Set<Integer>  index){
        this.index.addAll(index);
    }

    public void addIndex(Integer  index){
        this.index.add(index);
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

    public NfaStateMachine getNfaStateMachine() {
        return nfaStateMachine;
    }

    public void setNfaStateMachine(NfaStateMachine nfaStateMachine) {
        this.nfaStateMachine = nfaStateMachine;
    }

    public Set<Integer> getIndex() {
        return index;
    }

    public void setIndex(Set<Integer> index) {
        this.index = index;
    }

    public boolean isOr() {
        return isOr;
    }

    public void setOr(boolean or) {
        isOr = or;
    }

}
