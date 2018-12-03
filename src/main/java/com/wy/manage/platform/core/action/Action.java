package com.wy.manage.platform.core.action;

import com.wy.manage.platform.core.parser.ModelParam;

import java.util.List;

/**
 * Created by tianye
 * 代替RelevanceHandle
 */
public interface Action {
    void action(ModelParam modelParam);
    String getName();
    String getValue();
    void setValue(String value);
    List<String> getGroupNames();
    int getPriority();
}
