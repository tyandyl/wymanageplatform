package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class ModelParam<T> {
    private NfaStateNode startNode =null;
    private NfaStateNode startNodeBackup =null;
    private int curInt=0;
    private Stack<NfaStateNodeRecord> stackNodes=new Stack<NfaStateNodeRecord>();
    private char[] chars=null;
    private int num=0;//次数
    private List<Character> curModelValue=new ArrayList<Character>();
    private T t;

    public ModelParam analyzeIsBack(){
        if(stackNodes.empty()){
            ++num;
            curInt=0;
            startNode=startNodeBackup;
            return this;
        }
        NfaStateNodeRecord pop = stackNodes.pop();
        if(pop.getcRecord()>=0){
            --curInt;
            System.out.println("字符不匹配,退位"+chars[curInt]);
        }
        this.setStartNode(pop.getNfaStateNode());
        return this;
    }

    public ModelParam(NfaStateNode startNode,char[] chars,T t){
        this.startNode=startNode;
        this.startNodeBackup=startNode;
        this.curInt=0;
        this.chars=chars;
        this.t=t;
    }

    public NfaStateNode getStartNode() {
        return startNode;
    }

    public void setStartNode(NfaStateNode startNode) {
        this.startNode = startNode;
    }

    public int getCurInt() {
        return curInt;
    }

    public ModelParam setCurInt(int curInt) {
        this.curInt = curInt;
        return this;
    }

    public Stack<NfaStateNodeRecord> getStackNodes() {
        return stackNodes;
    }

    public void setStackNodes(Stack<NfaStateNodeRecord> stackNodes) {
        this.stackNodes = stackNodes;
    }

    public char[] getChars() {
        return chars;
    }

    public void setChars(char[] chars) {
        this.chars = chars;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<Character> getCurModelValue() {
        return curModelValue;
    }

    public void setCurModelValue(List<Character> curModelValue) {
        this.curModelValue = curModelValue;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
