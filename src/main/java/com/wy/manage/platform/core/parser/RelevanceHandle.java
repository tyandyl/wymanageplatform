package com.wy.manage.platform.core.parser;

/**
 * Created by tianye
 */
public interface RelevanceHandle<T> {
    void handle(ModelParam modelParam,T t);
}
