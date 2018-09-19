package com.wy.manage.platform.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tianye
 */
public class CharacterCarveContext {
    private List<Integer> specialCclStart=new ArrayList<Integer>();
    private List<Integer> specialCurlyStart=new ArrayList<Integer>();
    private List<Integer> specialParenStart=new ArrayList<Integer>();
    private List<Integer> specialAtBol=new ArrayList<Integer>();
    //放进栈中的item都有自动机
    private Stack<XContentItem> stack=new Stack<XContentItem>();

    private List<Integer> charCurly=new ArrayList<Integer>();

    public List<Integer> getSpecialCclStart() {
        return specialCclStart;
    }

    public void setSpecialCclStart(List<Integer> specialCclStart) {
        this.specialCclStart = specialCclStart;
    }

    public List<Integer> getSpecialCurlyStart() {
        return specialCurlyStart;
    }

    public void setSpecialCurlyStart(List<Integer> specialCurlyStart) {
        this.specialCurlyStart = specialCurlyStart;
    }

    public List<Integer> getSpecialParenStart() {
        return specialParenStart;
    }

    public void setSpecialParenStart(List<Integer> specialParenStart) {
        this.specialParenStart = specialParenStart;
    }

    public List<Integer> getSpecialAtBol() {
        return specialAtBol;
    }

    public void setSpecialAtBol(List<Integer> specialAtBol) {
        this.specialAtBol = specialAtBol;
    }

    public Stack<XContentItem> getStack() {
        return stack;
    }

    public void setStack(Stack<XContentItem> stack) {
        this.stack = stack;
    }

    public List<Integer> getCharCurly() {
        return charCurly;
    }

    public void setCharCurly(List<Integer> charCurly) {
        this.charCurly = charCurly;
    }
}
