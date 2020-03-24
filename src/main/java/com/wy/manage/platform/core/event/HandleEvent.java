package com.wy.manage.platform.core.event;

import java.util.EventObject;

/**
 * Created by tianye
 */
public class HandleEvent extends EventObject {
    private static final long serialVersionUID = -7278179041914054694L;

    String handleState;

    public HandleEvent(Object source, String handleState){
        super(source);
        this.handleState=handleState;
    }

    public String getHandleState() {
        return handleState;
    }

    public void setHandleState(String handleState) {
        this.handleState = handleState;
    }
}
