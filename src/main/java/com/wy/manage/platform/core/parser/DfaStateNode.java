package com.wy.manage.platform.core.parser;

import com.wy.manage.platform.core.action.Action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye
 */
public class DfaStateNode implements Serializable {
    private static final long serialVersionUID = -5374074793095261228L;
    //状态编号
    private String stateNum;
    private List<Action> action=new ArrayList<Action>();

    public String getStateNum() {
        return stateNum;
    }

    public void setStateNum(String stateNum) {
        this.stateNum = stateNum;
    }

    public List<Action> getAction() {
        return action;
    }

    public void setAction(List<Action> action) {
        this.action = action;
    }
}
