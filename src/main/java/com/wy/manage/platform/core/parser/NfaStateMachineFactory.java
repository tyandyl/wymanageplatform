package com.wy.manage.platform.core.parser;

/**
 * Created by tianye13 on 2018/9/9.
 */
public class NfaStateMachineFactory {

    public static NfaStateMachineBuilder getCCLNfaStateMachineBuilder(){
        CCLNfaStateMachineBuilder cclNfaStateMachineBuilder=new CCLNfaStateMachineBuilder();
        return cclNfaStateMachineBuilder;
    }
}
