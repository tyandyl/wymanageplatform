package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.*;

/**
 * Created by tianye
 */
public class ModelParam<T> {
    private int curInt=0;
    private Stack<NfaStateNodeRecord> stackNodes=new Stack<NfaStateNodeRecord>();
    private char[] chars=null;
    private List<Character> curModelValue=new ArrayList<Character>();
    private T t;

    public DfaContext handleMapFirst(NfaStateMachine var)throws Exception{
        final DfaContext context=new DfaContext();
        context.setStartNodeStateNum(var.getStartNode().getStateNum());
        NfaManager.traverse(var.getStartNode(),  new NodeHandle<NfaStateNode>() {
            public void handle(NfaStateNode o, int i)throws Exception {
                handleMapSec(o,context, i);
            }
        });
        return context;
    }

    /**
     * 处理辅助数据
     * @param curDfa
     * @param var
     * @param isClear 是否需要清理，一旦执行动作，边需要清理
     */
    public void addInfo(List<String> curDfa,int var,boolean isClear){
        NfaStateNodeRecord nfaStateNodeRecord=new NfaStateNodeRecord(curDfa,var);
        stackNodes.push(nfaStateNodeRecord);
        if(!isClear){
            curModelValue.add((char) var);
        }
        curInt++;
    }

    private void handleMapSec(NfaStateNode o, DfaContext context, int i)throws Exception{
        //建立状态码与节点的对应关系
        context.putMapState(o.getStateNum(),o);
        //获取状态值
        String stateNum = o.getStateNum();
        Map<Integer, List<String>> integerListMap = context.getMap().get(stateNum);
        if(integerListMap==null){
            //没有的话，赋值
            integerListMap=new HashMap<Integer, List<String>>();
            context.getMap().put(stateNum,integerListMap);
        }

        //判断是开始节点
        if(o.getState().getState()==NfaState.START.getState()){
            context.setStartNodeStateNum(o.getStateNum());
        }

        //判断是结束节点
        if(i==3){
            context.setEndNodeStateNum(o.getStateNum());
            return;
        }

        //获取当前条线
        EdgeLine edgeLine = o.getEdgeLines()[i];
        if(edgeLine==null){
            System.out.println("ss");
        }
        EdgeInputType edgeInputType = edgeLine.getEdgeInputType();
        //如果当前条线的类型是空
        if(edgeInputType==EdgeInputType.NULL_GATHER){
            List<Character> edgeAllowInputGather = edgeLine.getEdgeAllowInputGather();
            if(edgeAllowInputGather!=null && edgeAllowInputGather.size()>0){
                ExceptionTools.ThrowException("空集里边不允许有值，请排查");
            }
            //记录列
            context.getInputParams().add(130);
            context.handleMapCol(integerListMap,130,edgeLine.getNext().getStateNum());
        }else {
            List<Character> edgeAllowInputGather = edgeLine.getEdgeAllowInputGather();
            if(edgeAllowInputGather==null || edgeAllowInputGather.size()==0){
                ExceptionTools.ThrowException("条线里边必须有值，请排查");
            }
            //单个字符处理
            if(edgeAllowInputGather.size()==1){
                context.getInputParams().add(Integer.valueOf(edgeAllowInputGather.get(0)));

                context.handleMapCol(integerListMap,
                        Integer.valueOf(edgeAllowInputGather.get(0))
                        ,edgeLine.getNext().getStateNum());
            }else{
                //字符集处理，化为单个列处理
                for(Character ch:edgeAllowInputGather){
                    context.getInputParams().add(Integer.valueOf(ch));
                    context.handleMapCol(integerListMap,Integer.valueOf(ch),edgeLine.getNext().getStateNum());
                }

            }
        }
    }

    public ModelParam(T t,char[] chars){
        this.t=t;
        this.chars=chars;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getCurInt() {
        return curInt;
    }

    public void setCurInt(int curInt) {
        this.curInt = curInt;
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

    public List<Character> getCurModelValue() {
        return curModelValue;
    }

    public void setCurModelValue(List<Character> curModelValue) {
        this.curModelValue = curModelValue;
    }
}
