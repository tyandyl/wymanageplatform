package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye on 2018/9/8.
 */
public class XContent {
    //左大括号
    private List<Integer> leftBrace=new ArrayList<Integer>();
    //左小括号
    private List<Integer> leftParenthesis=new ArrayList<Integer>();
    //左中括号
    private List<Integer> leftBracket=new ArrayList<Integer>();
    //反斜杠\
    private List<Integer> backslash=new ArrayList<Integer>();
    //正斜杠/
    private List<Integer> backslashOpposite=new ArrayList<Integer>();
    //通配符.
    private List<Integer> wildcard=new ArrayList<Integer>();
}
