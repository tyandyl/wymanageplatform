package com.wy.manage.platform.core.widget;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianye13 on 2019/2/22.
 */
public class JParam implements Serializable {
    private static final long serialVersionUID = -3670349063954746718L;


    Map<String, String[]> parameterMap=null;

    public JParam(Map<String, String[]> param){
        this.parameterMap= new HashMap<String, String[]>(param);
    }

    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public String[] get(String param){
        return parameterMap.get(param);
    }

}
