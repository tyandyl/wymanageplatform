package com.wy.manage.platform.core.parser;

/**
 * Created by tianye13 on 2019/1/11.
 */
public interface MoveHandle<T,E> {
    /**
     *
     * @param t 状态
     * @param e 结果集，存放寻找的结果
     * @param var 条件ε，由哪条 条线寻找
     */
    void move(T t,E e,Integer var);
}
