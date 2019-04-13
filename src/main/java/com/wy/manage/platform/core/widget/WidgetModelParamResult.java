package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.bean.Result;

import javax.servlet.http.HttpServletRequest;
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

    private HandleType handleType;

    private Map<String,String> param=new HashMap<String, String>();

    private RegisterEvent registerEvent=new RegisterEvent();


    private HttpServletRequest request;

    private Result result=new Result();

    public WidgetModelParamResult(JParam param,HttpServletRequest request){
        this.JParam=param;
        this.request=request;
    }

    public WidgetModelParamResult(){

    }

    public JParam getJParam() {
        return JParam;
    }

    public void setJParam(JParam JParam) {
        this.JParam = JParam;
    }

    public HandleType getHandleType() {
        return handleType;
    }

    public void setHandleType(HandleType handleType) {
        this.handleType = handleType;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }


    public RegisterEvent getRegisterEvent() {
        return registerEvent;
    }

    public void setRegisterEvent(RegisterEvent registerEvent) {
        this.registerEvent = registerEvent;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
