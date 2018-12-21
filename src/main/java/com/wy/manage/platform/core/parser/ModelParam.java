package com.wy.manage.platform.core.parser;

import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.wy.manage.platform.core.utils.AtomicTools;
import com.wy.manage.platform.core.utils.ExceptionTools;

import java.util.*;

/**
 * Created by tianye
 */
public class ModelParam<T> {
    private int curInt=0;
    private char[] chars=null;
    private StringBuffer curModelValue=new StringBuffer();
    private T t;
    private Map<String,StringBuffer> regularValue=new HashMap<String, StringBuffer>();
    private Map<String,Integer> regularNum=new HashMap<String, Integer>();
    //锁住组内正则
    private List<String> lockRegularName=new ArrayList<String>();

    private Map<String,CssBag> map=new HashMap<String, CssBag>();

    public DfaContext initDfaContext(NfaStateMachine parser)throws Exception{
        DfaContext context=new DfaContext();
        return handleMapFirst(context,parser);
    }

    public DfaContext handleMapFirst(final DfaContext context, NfaStateMachine var)throws Exception{
        context.setStartNodeStateNum(var.getStartNode().getStateNum());
        final Integer num = AtomicTools.getBiUniqueInteger();
        NfaManager.traverse(var.getStartNode(),  new NodeHandle<NfaStateNode>() {
            public void handle(NfaStateNode o)throws Exception {
                if(o.getObjectId()!=num){
                    o.setObjectId(num);
                    handleMapSec(o,context);
                }
            }
        },num);
        return context;
    }

    /**
     * 处理辅助数据
     */
    public void addCurInt(){
        curInt++;
    }

    public void recordCurModelValue(char i){
        curModelValue.append(i);
    }
    public void clearCurModelValue(){
        curModelValue=new StringBuffer();
    }


    private void handleMapSec(NfaStateNode o, DfaContext context)throws Exception{
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
        if(o.getState()==NfaState.END){
            context.setEndNodeStateNum(o.getStateNum());
        }
        EdgeLine[] edgeLines = o.getEdgeLines();
        if(edgeLines!=null){
            for(int i=0;i<2;i++){
                //获取当前条线
                EdgeLine edgeLine = o.getEdgeLines()[i];
                if(edgeLine==null){
                    continue;
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

    public char[] getChars() {
        return chars;
    }

    public void setChars(char[] chars) {
        this.chars = chars;
    }

    public StringBuffer getCurModelValue() {
        return curModelValue;
    }

    public Map<String, StringBuffer> getRegularValue() {
        return regularValue;
    }

    public void setRegularValue(Map<String, StringBuffer> regularValue) {
        this.regularValue = regularValue;
    }

    public Map<String, Integer> getRegularNum() {
        return regularNum;
    }

    public void setRegularNum(Map<String, Integer> regularNum) {
        this.regularNum = regularNum;
    }

    /**
     *
     * @param key
     * @param num
     * @return
     */
    public boolean addRegularNum(String key,Integer num){
        Integer integer = regularNum.get(key);
        if(integer!=null && integer.intValue()==num.intValue()){
            return false;
        }
        regularNum.put(key,num);
        return true;

    }

    public List<String> getLockRegularName() {
        return lockRegularName;
    }

    public void addLockRegularName(String str){
        if(!lockRegularName.contains(str)){
            lockRegularName.add(str);
        }
    }

    public void setLockRegularName(List<String> lockRegularName) {
        this.lockRegularName = lockRegularName;
    }

    public Map<String, CssBag> getMap() {
        return map;
    }

    public void setMap(Map<String, CssBag> map) {
        this.map = map;
    }
}
