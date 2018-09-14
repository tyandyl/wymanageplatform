package com.wy.manage.platform.core.parser;

/**
 * Created by tianye on 2018/9/9.
 */
public class NfaStateMachineFactory {

    public static NfaStateMachineBuilder getCCLNfaStateMachineBuilder(){
        CCLNfaStateMachineBuilder cclNfaStateMachineBuilder=new CCLNfaStateMachineBuilder();
        return cclNfaStateMachineBuilder;
    }

    public static NfaStateMachineBuilder getCURLYNfaStateMachineBuilder(){
        CURLYNfaStateMachineBuilder curlyNfaStateMachineBuilder=new CURLYNfaStateMachineBuilder();
        return curlyNfaStateMachineBuilder;
    }

    public static NfaStateMachineBuilder getORNfaStateMachineBuilder(){
        ORNfaStateMachineBuilder orNfaStateMachineBuilder=new ORNfaStateMachineBuilder();
        return orNfaStateMachineBuilder;
    }

    public static NfaStateMachineBuilder getOPTIONALNfaStateMachineBuilder(){
        OPTIONALNfaStateMachineBuilder optionalNfaStateMachineBuilder=new OPTIONALNfaStateMachineBuilder();
        return optionalNfaStateMachineBuilder;
    }

    public static NfaStateMachineBuilder getCLOSURENfaStateMachineBuilder(){
        CLOSURENfaStateMachineBuilder closureNfaStateMachineBuilder=new CLOSURENfaStateMachineBuilder();
        return closureNfaStateMachineBuilder;
    }

    public static NfaStateMachineBuilder getPARENNfaStateMachineBuilder(){
        PARENNfaStateMachineBuilder parenNfaStateMachineBuilder=new PARENNfaStateMachineBuilder();
        return parenNfaStateMachineBuilder;
    }
}
