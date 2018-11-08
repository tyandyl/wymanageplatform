package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.action.Action;
import com.wy.manage.platform.core.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by tianye
 */
public class RegularExpressionParser {

    public static Stack<XContentItem> parser(char[] array, Map<String,Action> actions,boolean isOr) throws Exception {
        CharacterCarveContext context=new CharacterCarveContext();
        context.setActions(actions);
        context.setOr(isOr);
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
                    int carve1 = dashCharacterCarveCapacity.carve(context, array, i);
                    if(carve1==1){
                        i++;
                    }
                    break;
                case OPTIONAL:
                    CharacterCarveCapacity optionalCharacterCarveCapacity = NfaStateMachineFactory.getOptionalCharacterCarveCapacity();
                    optionalCharacterCarveCapacity.carve(context,array,i);
                    break;
                case OR:
                    CharacterCarveCapacity orCharacterCarveCapacity = NfaStateMachineFactory.getOrCharacterCarveCapacity();
                    int carve3 = orCharacterCarveCapacity.carve(context, array, i);
                    i=(i+carve3);
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
                    int carve2 = parenClosedCharacterCarveCapacity.carve(context, array, i);
                    //carve2是)的位置
                    if(carve2>0){
                        Stack<XContentItem> stack = context.getStack();
                        XContentItem peek = stack.peek();
                        peek.setBigger(carve2);
                        return stack;
                    }
                    break;
                case COMMA:
                    CharacterCarveCapacity commaCharacterCarveCapacity = NfaStateMachineFactory.getCommaCharacterCarveCapacity();
                    int carveComma=commaCharacterCarveCapacity.carve(context,array,i);
                    if(carveComma==1){
                        i++;
                    }
                    break;
                case PLUS:
                    CharacterCarveCapacity plusCharacterCarveCapacity = NfaStateMachineFactory.getPlusCharacterCarveCapacity();
                    plusCharacterCarveCapacity.carve(context,array,i);
                    break;
                case LP:
                    //和空格使用一个
                    CharacterCarveCapacity lpCharacterCarveCapacity = NfaStateMachineFactory.getLpCharacterCarveCapacity();
                    lpCharacterCarveCapacity.carve(context,array,i);
                    break;
                case CR:
                    CharacterCarveCapacity crCharacterCarveCapacity = NfaStateMachineFactory.getCrCharacterCarveCapacity();
                    crCharacterCarveCapacity.carve(context,array,i);
                    break;
                case SP:
                    //正则表达式空格的话，也应该当做一个输入处理
                    CharacterCarveCapacity spCharacterCarveCapacity = NfaStateMachineFactory.getSpCharacterCarveCapacity();
                    spCharacterCarveCapacity.carve(context,array,i);
                    break;
                default:
                    CharacterCarveCapacity lCharacterCarveCapacity = NfaStateMachineFactory.getLCharacterCarveCapacity();
                    lCharacterCarveCapacity.carve(context,array,i);
                    //普通字符
                    break;
            }

        }
        Stack<XContentItem> stack = context.getStack();
        if(stack.size()>1){
            System.out.println("大于1，报错");
        }
        return stack;
    }


}
