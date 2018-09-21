package com.wy.manage.platform.core.parser;

/**
 * Created by tianye13 on 2018/9/19.
 */
public interface RelevanceHandle<T> {
    void handle(ModelParam modelParam,T t);
}
