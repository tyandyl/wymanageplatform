package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.widget.SelectorType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye13 on 2018/9/21.
 */
public class CssBag {
    private SelectorType selectorType;
    private String name;
    private Map<String,List<String>> map=new HashMap<String, List<String>>();
    //存放属性值,过滤用
    private List<String> values=new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, List<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<String>> map) {
        this.map = map;
    }

    public SelectorType getSelectorType() {
        return selectorType;
    }

    public void setSelectorType(SelectorType selectorType) {
        this.selectorType = selectorType;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
