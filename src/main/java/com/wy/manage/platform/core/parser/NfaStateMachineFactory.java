package com.wy.manage.platform.core.parser;

/**
 * Created by tianye on 2018/9/9.
 */
public class NfaStateMachineFactory {

    public static CharacterCarveCapacity getAnyCharacterCarveCapacity(){
        return new AnyCharacterCarveCapacity();
    }
    public static CharacterCarveCapacity getAtBolCharacterCarveCapacity(){
        return new AtBolCharacterCarveCapacity();
    }
    public static CharacterCarveCapacity getAtEolCharacterCarveCapacity(){
        return new AtEolCharacterCarveCapacity();
    }

    public static CharacterCarveCapacity getCclCharacterCarveCapacity(){
        CharacterCarveCapacity cclCharacterCarveCapacity=new CclCharacterCarveCapacity();
        return cclCharacterCarveCapacity;
    }

    public static CharacterCarveCapacity getCURLYCharacterCarveCapacity(){
        CharacterCarveCapacity curlyCharacterCarveCapacity=new CurlyCharacterCarveCapacity();
        return curlyCharacterCarveCapacity;
    }

    public static CharacterCarveCapacity getCLOSURECharacterCarveCapacity(){
        ClosureCharacterCarveCapacity closureNfaStateMachineBuilder=new ClosureCharacterCarveCapacity();
        return closureNfaStateMachineBuilder;
    }

    public static CharacterCarveCapacity getDashCharacterCarveCapacity(){
        DashCharacterCarveCapacity dashCharacterCarveCapacity=new DashCharacterCarveCapacity();
        return dashCharacterCarveCapacity;
    }
    public static NfaStateMachineBuilder getORNfaStateMachineBuilder(){
        ORNfaStateMachineBuilder orNfaStateMachineBuilder=new ORNfaStateMachineBuilder();
        return orNfaStateMachineBuilder;
    }

    public static NfaStateMachineBuilder getOPTIONALNfaStateMachineBuilder(){
        OptionalCharacterCarveCapacity optionalNfaStateMachineBuilder=new OptionalCharacterCarveCapacity();
        return optionalNfaStateMachineBuilder;
    }



    public static NfaStateMachineBuilder getPARENNfaStateMachineBuilder(){
        PARENNfaStateMachineBuilder parenNfaStateMachineBuilder=new PARENNfaStateMachineBuilder();
        return parenNfaStateMachineBuilder;
    }
}
