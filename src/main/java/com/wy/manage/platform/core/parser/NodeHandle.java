package com.wy.manage.platform.core.parser;

/**
 * Created by tianye
 * 节点操作，递归节点时，对每个节点要做什么操作
 */
public interface NodeHandle<T> {
    void handle(T t,int i)throws Exception;
}
