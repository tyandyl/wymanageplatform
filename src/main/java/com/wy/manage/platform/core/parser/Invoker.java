package com.wy.manage.platform.core.parser;

/**
 * Created by tianye
 */
public interface Invoker<T,E,M,U> {
    void invoke(T t,E e,Integer var,M m,U u)throws Exception;
}
