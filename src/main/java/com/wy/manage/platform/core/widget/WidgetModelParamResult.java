package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.bean.Result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/28.
 */
public class WidgetModelParamResult extends Result implements Serializable {
    private static final long serialVersionUID = 3756887230492045598L;

    private JParam JParam;

    private AddType addType;

    private Map<String,String> param=new HashMap<String, String>();

    private List<CurWidget> curWidgets=new ArrayList<CurWidget>();



    public JParam getJParam() {
        return JParam;
    }

    public void setJParam(JParam JParam) {
        this.JParam = JParam;
    }

    public List<CurWidget> getCurWidgets() {
        return curWidgets;
    }

    public void setCurWidgets(List<CurWidget> curWidgets) {
        this.curWidgets = curWidgets;
    }

    public AddType getAddType() {
        return addType;
    }

    public void setAddType(AddType addType) {
        this.addType = addType;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
