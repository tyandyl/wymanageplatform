package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class ModelParam {
    private NfaStateNode startNode =null;
    private NfaStateNode startNodeBackup =null;
    private int curInt=0;
    private Stack<NfaStateNodeRecord> stackNodes=new Stack<NfaStateNodeRecord>();
    private char[] chars=null;
    private int num=0;//次数
    private List<Character> curModelValue=new ArrayList<Character>();
    //遇到[a-z1-9]+\{
    //有转移符号,回退后，该节点有两条边，第一条边是转移+，每次都走这条，所以转移优先2
    private int i=0;//优先路径

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
        i=1;
        return this;
    }

    public ModelParam(NfaStateNode startNode,char[] chars){
        this.startNode=startNode;
        this.startNodeBackup=startNode;
        this.curInt=0;
        this.chars=chars;
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

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
