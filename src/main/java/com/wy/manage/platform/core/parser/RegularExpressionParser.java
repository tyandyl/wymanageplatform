package com.wy.manage.platform.core.parser;

import java.util.Stack;

/**
 * Created by tianye on 2018/9/8.
 */
public class RegularExpressionParser {

    public static NfaStateMachine parser(String str){
        char[] arry=str.toCharArray();
        for(int i=0;i<arry.length;i++){
            SymbolType symbolType = SymbolType.findSymbolType(arry[i]);
            if(symbolType==null){
                switch (arry[i]){
                    case 13://回车
                        break;
                    case 30://空格
                        break;
                    case 10://换行
                        break;
                    default:
                        line.append((char)ch);
                        break;
                }
            }
        }
        return null;
    }

}
