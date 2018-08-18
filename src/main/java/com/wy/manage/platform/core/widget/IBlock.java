package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.Properties;

import java.util.List;

/**
 * Created by tianye on 2018/6/27.
 */
public interface IBlock {

    String PIXEL_UNIT="px";

    Properties getProperties();

    void setProperties(Properties properties);

    TagType getTagType();

    void setTagType(TagType tagType);

    List<IBlock> getChildren();

    void setChildren(List<IBlock> list);

    void invoke();



}
