package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.widget.SelectorType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianye13 on 2018/9/21.
 */
public class CssBag {
    private SelectorType selectorType;
    private String name;
    private Map<String,String> map=new HashMap<String, String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public SelectorType getSelectorType() {
        return selectorType;
    }

    public void setSelectorType(SelectorType selectorType) {
        this.selectorType = selectorType;
    }
}
