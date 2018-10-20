package com.wy.manage.platform.core;

import com.wy.manage.platform.core.parser.NfaManager;
import com.wy.manage.platform.core.parser.NfaStateMachine;

/**
 * Created by tianye on 2018/6/29.
 */
public class Test {
    public static void main(String[] agrs) throws Exception {
//        NfaStateMachine parser = CssParser.parser();
//        CssBag cssBag=new CssBag();
//        AnalyzeExecuteModel.execute("#sh{",cssBag,parser);
        NfaStateMachine simplestNfaStateMachine = NfaManager.createSimplestNfaStateMachine(true);
        NfaStateMachine nfaStateMachine = NfaManager.deepClone(simplestNfaStateMachine);
        System.out.println("第一个编号："+simplestNfaStateMachine.getStartNode().getStateNum());
        System.out.println("第二个编号："+nfaStateMachine.getStartNode().getStateNum());
    }
}
