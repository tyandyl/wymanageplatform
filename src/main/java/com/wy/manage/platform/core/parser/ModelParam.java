package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.AtomicTools;
import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.*;

/**
 * Created by tianye
 */
public class ModelParam<T> {
    private int curInt=0;
    private Stack<NfaStateNodeRecord> stackNodes=new Stack<NfaStateNodeRecord>();
    private char[] chars=null;
    private StringBuffer curModelValue=new StringBuffer();
    private T t;
    private Stack<HandleInfo> handleInfo=new Stack<HandleInfo>();

    public DfaContext initDfaContext(NfaStateMachine parser)throws Exception{
        DfaContext context=new DfaContext();
        return handleMapFirst(context,parser);
    }

    public DfaContext handleMapFirst(final DfaContext context, NfaStateMachine var)throws Exception{
        context.setStartNodeStateNum(var.getStartNode().getStateNum());
        NfaManager.traverse(var.getStartNode(),  new NodeHandle<NfaStateNode>() {
            public void handle(NfaStateNode o, int i)throws Exception {
                handleMapSec(o,context, i);
            }
        },AtomicTools.getBiUniqueInteger());
        return context;
    }

    /**
     * 处理辅助数据
     * @param curDfa
     * @param var
     */
    public void addCurInt(Set<String> curDfa,int var){
        NfaStateNodeRecord nfaStateNodeRecord=new NfaStateNodeRecord(curDfa,var);
        stackNodes.push(nfaStateNodeRecord);
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
        TreeSet<Integer> integers = new TreeSet<Integer>();
        //如果当前条线的类型是空
        if(edgeInputType==EdgeInputType.NULL_GATHER){
            List<Character> edgeAllowInputGather = edgeLine.getEdgeAllowInputGather();
            if(edgeAllowInputGather!=null && edgeAllowInputGather.size()>0){
                ExceptionTools.ThrowException("空集里边不允许有值，请排查");
            }
            //记录列
            context.addInputParam(130,integers);
            context.handleMapCol(integerListMap,130,edgeLine.getNext().getStateNum());
        }else {
            List<Character> edgeAllowInputGather = edgeLine.getEdgeAllowInputGather();
            if(edgeAllowInputGather==null || edgeAllowInputGather.size()==0){
                ExceptionTools.ThrowException("条线里边必须有值，请排查");
            }
            //单个字符处理
            if(edgeAllowInputGather.size()==1){
                context.addInputParam(Integer.valueOf(edgeAllowInputGather.get(0)),integers);

                context.handleMapCol(integerListMap,
                        Integer.valueOf(edgeAllowInputGather.get(0))
                        ,edgeLine.getNext().getStateNum());
            }else{
                //字符集处理，化为单个列处理
                for(Character ch:edgeAllowInputGather){
                    integers.add(Integer.valueOf(ch));
                    context.handleMapCol(integerListMap,Integer.valueOf(ch),edgeLine.getNext().getStateNum());
                }
                context.addInputParam((160+AtomicTools.getBiUniqueInteger()),integers);

            }
        }
    }

    public ModelParam(T t,char[] chars){
        this.t=t;
        this.chars=chars;
    }



    public Stack<HandleInfo> getHandleInfo() {
        return handleInfo;
    }

    public void setHandleInfo(Stack<HandleInfo> handleInfo) {
        this.handleInfo = handleInfo;
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

    public StringBuffer getCurModelValue() {
        return curModelValue;
    }

    public ModelParam setCurModelValue(StringBuffer curModelValue) {
        this.curModelValue = curModelValue;
        return this;
    }
}
