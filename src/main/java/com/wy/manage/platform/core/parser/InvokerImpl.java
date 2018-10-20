package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.utils.ExceptionTools;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by tianye
 */
public class InvokerImpl implements Invoker {
    private String regularStr;
    private long sTid = 0L;
    private boolean isPrint = false;
    private RelevanceHandle handle;

    public InvokerImpl(String regularStr) {
        this.regularStr = regularStr;
    }


    public InvokerImpl relevance(RelevanceHandle handle){
        this.handle=handle;
        return this;
    }

    public InvokerImpl setIsPrint(boolean isPrint){
        this.isPrint=isPrint;
        return this;
    }


    public NfaStateMachine invoke() {
        NfaStateMachine t=null;
        try {
            this.sTid = System.currentTimeMillis();
            t=this.onInvoke(this.preInvoke(),handle);
        } catch (Exception e) {
        } finally {
        }
        return t;
    }

    private NfaStateMachine onInvoke(NfaStateMachine nfaStateMachine,RelevanceHandle handle) throws Exception{
        NfaStateNode endNode = nfaStateMachine.getEndNode();
        endNode.setHandle(handle);
        //traverse(nfaStateMachine.startNode, endNode,0);
        return nfaStateMachine;
    }

    public static void traverse(NfaStateNode startNode,NfaStateNode endNode,int i){
        if(startNode!=null){
            i++;
            if(startNode.getEdgeLines()[0]!=null){
                List<Character> edgeAllowInputGather = startNode.getEdgeLines()[0].getEdgeAllowInputGather();
                System.out.println("第"+i+"个节点的第1条边的输入是:"+(edgeAllowInputGather.size()==0?"空集":String.valueOf((Character[])edgeAllowInputGather.toArray())));
                if(startNode.getEdgeLines()[0].getNext()!=endNode){
                    traverse(startNode.getEdgeLines()[0].getNext(),endNode,i);
                }
            }
            if(startNode.getEdgeLines()[1]!=null){
                List<Character> edgeAllowInputGather = startNode.getEdgeLines()[1].getEdgeAllowInputGather();
                System.out.println("第"+i+"个节点的第2条边的输入是:"+(edgeAllowInputGather.size()==0?"空集":String.valueOf((Character[])edgeAllowInputGather.toArray())));
                if(startNode.getEdgeLines()[1].getNext()!=endNode){
                    traverse(startNode.getEdgeLines()[1].getNext(),endNode,i);
                }
            }
        }
    }

    private NfaStateMachine preInvoke() throws Exception {
        Stack<XContentItem> stack = RegularExpressionParser.parserCss(regularStr.toCharArray());
        if (stack.size() == 1) {
            XContentItem peek = stack.peek();
            NfaStateMachine nfaStateMachine = peek.getNfaStateMachine();
            if (this.isPrint) {
                System.out.println("开始解析正则表达式:" + regularStr);
            }
            return nfaStateMachine;
        } else {
            System.out.println("栈的长度是:" + stack.size());
            System.out.println("栈末级项的内容是:" + stack.peek().getIndex());
            ExceptionTools.ThrowException("解析失败,请检查");
        }
        return null;

    }

    private DfaContext handleMapFirst(NfaStateMachine var)throws Exception{
        //key是行数，代表状态
        //Value第一位是输入，这里使用整形0-127代表单个字符，130代表空，135代表字符集(字符集进一步处理，化为单个列)
        //Value第二位是下一个状态
        final Map<String,Map<Integer,List<String>>> map=new HashMap<String, Map<Integer, List<String>>>();
        NfaManager.traverse(var.getStartNode(), var.getEndNode(), new NodeHandle<NfaStateNode>() {
            public void handle(NfaStateNode o, int i)throws Exception {
                handleMapSec(o,map, i);
            }
        });
        DfaContext context=new DfaContext();
        context.setMap(map);
        return context;
    }

    private void handleMapSec(NfaStateNode o,Map<String,Map<Integer,List<String>>> map,int i)throws Exception{
        //获取状态值
        String stateNum = o.getStateNum();
        Map<Integer, List<String>> integerListMap = map.get(stateNum);
        //看看有没有当前行
        if(integerListMap!=null){
            //获取当前条线
            EdgeLine edgeLine = o.getEdgeLines()[i];
            EdgeInputType edgeInputType = edgeLine.getEdgeInputType();
            //如果当前条线的类型是空
            if(edgeInputType==EdgeInputType.NULL_GATHER){
                List<Character> edgeAllowInputGather = edgeLine.getEdgeAllowInputGather();
                if(edgeAllowInputGather!=null && edgeAllowInputGather.size()>0){
                    ExceptionTools.ThrowException("空集里边不允许有值，请排查");
                }
                //获取空列的下一个状态
                List<String> list = integerListMap.get(130);
                //如果下一个状态列表有值，则直接填充
                if(list!=null){
                    if(!list.contains(edgeLine.getNext().getStateNum())){
                        list.add(edgeLine.getNext().getStateNum());
                    }
                }else {
                    List<String> listNew=new ArrayList<String>();
                    listNew.add(edgeLine.getNext().getStateNum());
                    integerListMap.put(130,listNew);
                }
            }else {
                List<Character> edgeAllowInputGather = edgeLine.getEdgeAllowInputGather();
                if(edgeAllowInputGather==null || edgeAllowInputGather.size()==0){
                    ExceptionTools.ThrowException("条线里边必须有值，请排查");
                }
                //单个字符处理
                if(edgeAllowInputGather.size()==1){
                    //获取空列的下一个状态
                    List<String> list = integerListMap.get(edgeAllowInputGather.get(0));
                    //如果下一个状态列表有值，则直接填充
                    if(list!=null){
                        if(!list.contains(edgeLine.getNext().getStateNum())){
                            list.add(edgeLine.getNext().getStateNum());
                        }
                    }else {
                        List<String> listNew=new ArrayList<String>();
                        listNew.add(edgeLine.getNext().getStateNum());
                        Character character = edgeAllowInputGather.get(0);
                        integerListMap.put(Integer.valueOf(character),listNew);
                    }
                }else{
                    //字符集处理，化为单个列处理
                    for(Character ch:edgeAllowInputGather){
                        List<String> list = integerListMap.get(ch);
                        //如果下一个状态列表有值，则直接填充
                        if(list!=null){
                            if(!list.contains(edgeLine.getNext().getStateNum())){
                                list.add(edgeLine.getNext().getStateNum());
                            }
                        }else {
                            List<String> listNew=new ArrayList<String>();
                            listNew.add(edgeLine.getNext().getStateNum());
                            integerListMap.put(Integer.valueOf(ch),listNew);
                        }
                    }

                }
            }
        }else {
            //没有的话，赋值
            Map<Integer,List<String>> map1=new HashMap<Integer, List<String>>();
            map.put(stateNum,map1);
        }
    }
}
