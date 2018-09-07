package com.wy.manage.platform.core.widget;

/**
 * Created by tianye on 2018/7/1.
 */
public interface WidgetFactory {
    Object getWidget(String code) throws Exception;
}
