package com.wy.manage.platform.core;

/**
 * Created by tianye on 2018/7/1.
 */
public interface WidgetFactory {

    Object getWidget(String code) throws Exception;


    <T> Object getWidget(String name,Class<T> PropertyType) throws Exception;

    boolean containWidget(String code)throws Exception;

    boolean isSingleton(String code)throws Exception;


}
