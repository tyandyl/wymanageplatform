package com.wy.manage.platform.core.parser;

/**
 * Created by tianye
 */
public interface StateMoveHandle<T,E> {
    void move(T t,E e,Integer var);
}
