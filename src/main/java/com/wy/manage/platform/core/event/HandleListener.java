package com.wy.manage.platform.core.event;

import java.util.EventListener;

/**
 * Created by tianye
 */
public interface  HandleListener extends EventListener {
    public void doorEvent(HandleEvent event);
    //https://blog.csdn.net/feicongcong/article/details/76685848
}
