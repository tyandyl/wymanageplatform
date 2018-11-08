package com.wy.manage.platform.core.parser;

import java.util.*;

/**
 * Created by tianye
 */
public class XContentItem {
    private int legend;
    //index融合，数目较大
    private Set<Integer> index =new TreeSet<Integer>(new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    });
    private MeanType meanType;
    private NfaStateMachine nfaStateMachine=null;
    //|回退时，记录index
    private int bigger;

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

    public int getBigger() {
        return bigger;
    }

    public void setBigger(int bigger) {
        this.bigger = bigger;
    }
}
