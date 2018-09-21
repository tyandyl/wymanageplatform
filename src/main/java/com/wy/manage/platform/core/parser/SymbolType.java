package com.wy.manage.platform.core.parser;

/**
 * 正则表达式符号
 * Created by tianye
 */
public enum SymbolType {
    ANY(46,".通配符"),
    AT_BOL(94,"^开头匹配符"),
    AT_EOL(36,"$末尾匹配符"),
    CCL_END(93,"字符集类结尾括号]"),
    CCL_START(91,"字符集类开始括号["),
    CLOSE_CURLY(125,"}"),
    OPEN_CURLY(123,"{"),
    CLOSURE(42,"*"),
    DASH(45," -"),
    OPTIONAL(63,"?"),
    OR(124,"|"),
    BACKSLASH(92,"反斜杠\\"),
    OPEN_PAREN(40,"("),
    CLOSE_PAREN(41,")"),
    PLUS(43,"+"),
    LP(10,"换行"),
    CR(13,"回车"),
    SP(32,"空格"),
    L(999,"普通字符"),
    COMMA(44,"逗号");


    public int state;
    public String name;

    private SymbolType(int state, String name){
        this.state=state;
        this.name=name;
    }

    public static SymbolType findSymbolType(int code) {
        SymbolType[] values = SymbolType.values();
        for (SymbolType each : values) {
            if (each.getState() == code) {
                return each;
            }
        }
        return L;
    }

    public int getState() {
        return state;
    }

    public String getName() {
        return name;
    }
};

