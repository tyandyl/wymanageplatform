package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye on 2018/9/8.
 */
public class RegularExpressionParser {

    public static NfaStateMachine parserCss(String str) throws Exception {
        char[] array=str.toCharArray();
        CharacterCarveContext context=new CharacterCarveContext();
        for(int i=0;i<array.length;i++){
            SymbolType symbolType = SymbolType.findSymbolType(array[i]);
            switch (symbolType){
                case ANY:
                    CharacterCarveCapacity anyCharacterCarveCapacity = NfaStateMachineFactory.getAnyCharacterCarveCapacity();
                    anyCharacterCarveCapacity.carve(context,array,i);
                    break;
                case AT_BOL:
                    CharacterCarveCapacity atBolCharacterCarveCapacity = NfaStateMachineFactory.getAtBolCharacterCarveCapacity();
                    atBolCharacterCarveCapacity.carve(context,array,i);
                    break;
                case AT_EOL:
                    CharacterCarveCapacity atEolCharacterCarveCapacity = NfaStateMachineFactory.getAtEolCharacterCarveCapacity();
                    atEolCharacterCarveCapacity.carve(context,array,i);
                    break;
                case CCL_END:
                    CharacterCarveCapacity cclCharacterCarveCapacity = NfaStateMachineFactory.getCclClosedCharacterCarveCapacity();
                    cclCharacterCarveCapacity.carve(context,array,i);
                    break;
                case CCL_START:
                    CharacterCarveCapacity cclStartedCharacterCarveCapacity = NfaStateMachineFactory.getCclStartedCharacterCarveCapacity();
                    cclStartedCharacterCarveCapacity.carve(context,array,i);
                    break;
                case CLOSE_CURLY:
                    CharacterCarveCapacity curlyCharacterCarveCapacity = NfaStateMachineFactory.getCURLYClosedCharacterCarveCapacity();
                    curlyCharacterCarveCapacity.carve(context,array,i);
                    break;
                case OPEN_CURLY:
                    CharacterCarveCapacity curlyOpenedCharacterCarveCapacity = NfaStateMachineFactory.getCurlyOpenedCharacterCarveCapacity();
                    curlyOpenedCharacterCarveCapacity.carve(context,array,i);
                    break;
                case CLOSURE:
                    CharacterCarveCapacity closureNfaStateMachineBuilder = NfaStateMachineFactory.getCLOSURECharacterCarveCapacity();
                    closureNfaStateMachineBuilder.carve(context,array,i);
                    break;
                case DASH:
                    CharacterCarveCapacity dashCharacterCarveCapacity = NfaStateMachineFactory.getDashCharacterCarveCapacity();
                    dashCharacterCarveCapacity.carve(context,array,i);
                    break;
                case OPTIONAL:
                    CharacterCarveCapacity optionalCharacterCarveCapacity = NfaStateMachineFactory.getOptionalCharacterCarveCapacity();
                    optionalCharacterCarveCapacity.carve(context,array,i);
                    break;
                case OR:
                    CharacterCarveCapacity orCharacterCarveCapacity = NfaStateMachineFactory.getOrCharacterCarveCapacity();
                    orCharacterCarveCapacity.carve(context,array,i);
                    break;
                case BACKSLASH:
                    CharacterCarveCapacity backSlashCharacterCarveCapacity = NfaStateMachineFactory.getBackSlashCharacterCarveCapacity();
                    int carve = backSlashCharacterCarveCapacity.carve(context, array, i);
                    if(carve==1){
                        i++;
                    }
                    break;
                case OPEN_PAREN:
                    CharacterCarveCapacity parenStartedCharacterCarveCapacity = NfaStateMachineFactory.getPARENStartedCharacterCarveCapacity();
                    parenStartedCharacterCarveCapacity.carve(context,array,i);
                    break;
                case CLOSE_PAREN:
                    CharacterCarveCapacity parenClosedCharacterCarveCapacity = NfaStateMachineFactory.getPARENClosedCharacterCarveCapacity();
                    parenClosedCharacterCarveCapacity.carve(context,array,i);
                    break;
                case COMMA:
                    CharacterCarveCapacity commaCharacterCarveCapacity = NfaStateMachineFactory.getCommaCharacterCarveCapacity();
                    int carveComma=commaCharacterCarveCapacity.carve(context,array,i);
                    if(carveComma==2){
                        i++;
                        i++;
                    }
                default:
                    //普通字符
                    break;
            }

        }

        return null;
    }


}
