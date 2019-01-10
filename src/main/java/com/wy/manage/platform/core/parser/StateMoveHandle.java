package com.wy.manage.platform.core.parser;

import java.util.List;
import java.util.Set;

/**
 * Created by tianye
 */
public interface StateMoveHandle<T,E,M,U> {
    /**
     *
     * @param t 状态
     * @param e 结果集，存放寻找的结果
     * @param var 条件ε，由哪条 条线寻找
     * @param m 辅助工具
     * @param u 辅助工具
     */
    void analyze(T t,E e,Integer var,M m,U u)throws Exception;
}
