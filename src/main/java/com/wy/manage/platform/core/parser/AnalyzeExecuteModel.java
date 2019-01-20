package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.action.Action;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.StyleSheetType;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by tianye
 */
public class AnalyzeExecuteModel {

    private static Character[] specChars={32,10,13};

    public static void execute(ModelParam modelParam,NfaStateMachine parser) throws Exception {

        DfaContext context = modelParam.initDfaContext(parser).buildEmptyStateGather();
        execute(modelParam,context);
    }


    public static void execute(final ModelParam modelParam, final DfaContext context)throws Exception{
        //新的状态集合
        StateResult result=new StateResult();

        //获取开始节点
        Set<String> startNodes =context.getMapEmpty().get(context.getStartNodeStateNum());

        while (modelParam.getCurInt()<modelParam.getChars().length){
            if(modelParam.getChars()[modelParam.getCurInt()]=='A'){
                System.out.print("");
            }

            new AnalyzeStateMoveHandle().analyze(startNodes,result,Integer.valueOf(modelParam.getChars()[modelParam.getCurInt()]), context);
            if(result.getList().size()==0){
                System.out.println("当前字符不符合规则,当前字符是:"+modelParam.getChars()[modelParam.getCurInt()]
                        +",其积累的字符串是:"+modelParam.getCurModelValue());
                System.out.println("完毕");
                break;
            }

            for(String mm:result.getListRecord()){
                //对正则表达式的状态进行记录
                record( modelParam,   context, mm);
            }

            //说明有结束节点干扰的前进
            if(result.getNodes().size()>0){
                int check = check(modelParam.getChars(), modelParam.getCurInt(), result, context);
                if(check==1 || check==3){
                    //System.out.println("这个节点需要执行");
                    List<NfaStateNode> nodes = result.getNodes();
                    Collections.sort(nodes, new Comparator<NfaStateNode>() {
                        public int compare(NfaStateNode o1, NfaStateNode o2) {
                            int priority1 = o1.getPriority();
                            int priority2 = o2.getPriority();
                            if(priority1>priority2){
                                return -1;
                            }else if(priority1==priority2){
                                return 0;
                            }else {
                                return 0;
                            }
                        }
                    });
                    for(NfaStateNode node:nodes){
                        node.getAction().action(modelParam);
                        modelParam.getLockRegularName().add(node.getAction().getName());
                        //最高优先度执行完毕后需要清空，避免两个状态机衔接问题，一个没执行完，另一个就填充
                        if(node.getAction().getPriority()==1){
                            modelParam.getRegularValue().clear();
                            modelParam.getLockRegularName().clear();
                        }

                        if(node.getAction().getIntraGroupNames()!=null
                                && node.getAction().getIntraGroupNames().size()>0
                                && node.getAction().getPriority()>1){
                            for(String actionName:node.getAction().getIntraGroupNames()){
                                modelParam.getRegularValue().remove(actionName);
                                modelParam.getLockRegularName().remove(actionName);
                            }

                        }
                    }

                }
                if(check==2){
                    //System.out.println("这个节点不需要执行");
                }
            }
            //说明是没有结束节点干扰的前进
            if(result.getNodes().size()==0){

            }

            modelParam.recordCurModelValue(modelParam.getChars()[modelParam.getCurInt()]);

            modelParam.addCurInt();

            startNodes=new HashSet<String>(result.getList());
            result.clear();
        }
    }

    public static void record(ModelParam modelParam,  DfaContext context,String y){
        //获取新的状态
        NfaStateNode nfaStateNode = context.getMapState().get(y);
        EdgeLine[] edgeLines = nfaStateNode.getEdgeLines();
        if(edgeLines!=null
                && ((edgeLines[0]!=null && edgeLines[0].getEdgeAllowInputGather().size()>0)
                    || (edgeLines[1]!=null && edgeLines[1].getEdgeAllowInputGather().size()>0))){
            //判断下一个状态属于哪个正则
            List<String> belongRegulars = nfaStateNode.getBelongRegular();
            if(belongRegulars!=null && belongRegulars.size()>0){
                for(String belongRegular:belongRegulars){
                    if(belongRegular.equalsIgnoreCase("cssStop")){
                        System.out.print("");
                    }
                    if(StringUtils.isNotBlank(belongRegular)){
                        Map<String, StringBuffer> regularValue = modelParam.getRegularValue();
                        StringBuffer buffer = regularValue.get(belongRegular);
                        //对当前字符进行总结
                        int i = modelParam.getCurInt();
                        if(buffer==null){
                            if(modelParam.addRegularNum(belongRegular, i)){
                                StringBuffer buffer1=new StringBuffer();
                                buffer1.append(modelParam.getChars()[i]);
                                regularValue.put(belongRegular,buffer1);
                            }
                        }else if(!modelParam.getLockRegularName().contains(belongRegular)){
                            if(modelParam.addRegularNum(belongRegular, i)){
                                buffer.append(modelParam.getChars()[i]);
                                regularValue.put(belongRegular,buffer);
                            }

                        }
                    }
                }
            }
        }

    }

    public static int check(char[] chars,int curIntL,StateResult result, DfaContext context)throws Exception{
        //往后预测20位
        curIntL = curIntL+1;
        int guessInt=chars.length;
        //这里分为两大类，一类是不经过结束节点的集合，一类是结束节点集合
        //我们只需要判断这两大类哪一类能用就行
        Set<String> listL = result.getListL();
        Set<String> nodeStateNumList = result.getNodeStateNumList();
        StateResult resultA=new StateResult();
        StateResult resultB=new StateResult();
        int i=1;
        while (curIntL<guessInt){
            //System.out.println("检测走了"+i+"步");
            //不经过结束节点的集合解析
            new AnalyzeStateMoveHandle().analyze(listL,resultA,Integer.valueOf(chars[curIntL]), context);
            //说明不应该这样走，结束
            if(resultA.getList().size()==0){
                return 1;
            }
            if(resultA.getNodes().size()>0){
                //System.out.println("看来我熊大的优势较大");
            }
            listL=new HashSet<String>(resultA.getList());
            resultA.clear();
            //经过结束节点的集合解析
            new AnalyzeStateMoveHandle().analyze(nodeStateNumList,resultB,Integer.valueOf(chars[curIntL]), context);
            //说明不应该这样走，结束
            if(resultB.getList().size()==0){
                return 2;
            }
            if(resultB.getNodes().size()>0){
                //System.out.println("看来我熊二的优势较大");
            }
            nodeStateNumList=new HashSet<String>(resultB.getList());
            resultB.clear();
            i++;
            curIntL++;
        }
        return 3;
    }

}
