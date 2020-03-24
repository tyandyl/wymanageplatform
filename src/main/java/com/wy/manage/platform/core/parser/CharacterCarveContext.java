package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.action.Action;

import java.util.*;

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
    //存放{后的字符
    private List<Integer> charCurly=new ArrayList<Integer>();

    private boolean isOr;

    private Map<String,Action> actions=new HashMap<String, Action>();

    public Map<String, Action> getActions() {
        return actions;
    }

    public void setActions(Map<String, Action> actions) {
        this.actions = actions;
    }

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

    public boolean isOr() {
        return isOr;
    }

    public void setOr(boolean or) {
        isOr = or;
    }
}
