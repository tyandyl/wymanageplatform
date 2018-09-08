package com.wy.manage.platform.core.parser;

/**
 * 正则表达式符号，无/，/需要另外考虑
 * Created by tianye on 2018/9/7.
 */
public enum SymbolType {
    ANY(46,".通配符"),
    AT_BOL(94,"^开头匹配符"),
    AT_EOL(36,"$末尾匹配符"),
    CCL_END(93,"字符集类结尾括号]"),
    CCL_START(91,"字符集类开始括号["),
    CLOSE_CURLY(125,"}"),
    CLOSE_PAREN(41,")"),
    CLOSURE(42,"*"),
    DASH(45," -"),
    L(999,"字符常量"),
    OPEN_CURLY(123,"{"),
    OPEN_PAREN(40,"("),
    OPTIONAL(63,"?"),
    OR(124,"|");

    public int state;
    public String name;

    private SymbolType(int state, String name){
        this.state=state;
        this.name=name;
    }
    public int getState() {
        return state;
    }

    public String getName() {
        return name;
    }
};

