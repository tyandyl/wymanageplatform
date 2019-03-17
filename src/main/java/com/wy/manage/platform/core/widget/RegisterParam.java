package com.wy.manage.platform.core.widget;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * Created by tianye13 on 2019/3/12.
 */
public class RegisterParam implements Serializable{
    private static final long serialVersionUID = -8612722374388061764L;

    //请求参数
    private Set<String> requestParam=new HashSet<String>();
    //注册事件,VALUE=事件,辅助wd,辅助wd
    private Set<String> register=new HashSet<String>();

    public Set<String> getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(Set<String> requestParam) {
        this.requestParam = requestParam;
    }

    public Set<String> getRegister() {
        return register;
    }

    public void setRegister(Set<String> register) {
        this.register = register;
    }
}
