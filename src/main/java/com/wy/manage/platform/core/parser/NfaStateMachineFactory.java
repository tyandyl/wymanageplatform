package com.wy.manage.platform.core.parser;

/**
 * Created by tianye
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

    public static CharacterCarveCapacity getCclClosedCharacterCarveCapacity(){
        CharacterCarveCapacity cclCharacterCarveCapacity=new CclClosedCharacterCarveCapacity();
        return cclCharacterCarveCapacity;
    }
    public static CharacterCarveCapacity getCclStartedCharacterCarveCapacity(){
        CharacterCarveCapacity cclCharacterCarveCapacity=new CclStartedCharacterCarveCapacity();
        return cclCharacterCarveCapacity;
    }

    public static CharacterCarveCapacity getCURLYClosedCharacterCarveCapacity(){
        CharacterCarveCapacity curlyCharacterCarveCapacity=new CurlyClosedCharacterCarveCapacity();
        return curlyCharacterCarveCapacity;
    }

    public static CharacterCarveCapacity getCurlyOpenedCharacterCarveCapacity(){
        CharacterCarveCapacity curlyCharacterCarveCapacity=new CurlyOpenedCharacterCarveCapacity();
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
    public static CharacterCarveCapacity getOrCharacterCarveCapacity(){
        OrCharacterCarveCapacity orNfaStateMachineBuilder=new OrCharacterCarveCapacity();
        return orNfaStateMachineBuilder;
    }

    public static CharacterCarveCapacity getOptionalCharacterCarveCapacity(){
        OptionalCharacterCarveCapacity optionalNfaStateMachineBuilder=new OptionalCharacterCarveCapacity();
        return optionalNfaStateMachineBuilder;
    }



    public static CharacterCarveCapacity getPARENClosedCharacterCarveCapacity(){
        ParenClosedCharacterCarveCapacity parenNfaStateMachineBuilder=new ParenClosedCharacterCarveCapacity();
        return parenNfaStateMachineBuilder;
    }

    public static CharacterCarveCapacity getPARENStartedCharacterCarveCapacity(){
        ParenOpenedCharacterCarveCapacity parenNfaStateMachineBuilder=new ParenOpenedCharacterCarveCapacity();
        return parenNfaStateMachineBuilder;
    }

    public static CharacterCarveCapacity getBackSlashCharacterCarveCapacity(){
        return new BackSlashCharacterCarveCapacity();
    }

    public static CharacterCarveCapacity getCommaCharacterCarveCapacity(){
        return new CommaCharacterCarveCapacity();
    }
    public static CharacterCarveCapacity getLCharacterCarveCapacity(){
        return new LCharacterCarveCapacity();
    }
}
