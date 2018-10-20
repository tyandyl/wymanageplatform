package com.wy.manage.platform.core.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class DfaContext {
    Map<String,Map<Integer,List<String>>> map=new HashMap<String, Map<Integer, List<String>>>();

    public Map<String, Map<Integer, List<String>>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<Integer, List<String>>> map) {
        this.map = map;
    }
}
