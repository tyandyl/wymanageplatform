package com.wy.manage.platform.core;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/6/27.
 */
public interface IBlock {

    String PIXEL_UNIT="px";

    Map<PropertyType,List<Property>> getProperties();

    void setProperties(Map<PropertyType,List<Property>> properties);

    TagType getTagType();

    void setTagType(TagType tagType);

    List<IBlock> getChildren();

    void setChildren(List<IBlock> list);



}
