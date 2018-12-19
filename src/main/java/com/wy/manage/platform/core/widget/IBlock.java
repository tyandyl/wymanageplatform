package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.Properties;

import java.util.List;

/**
 * Created by tianye on 2018/6/27.
 */
public interface IBlock {

    String PIXEL_UNIT="px";

    /**
     * 获取属性信息
     * @return
     */
    Properties getProperties();

    /**
     * 设置属性信息
     * @param properties
     */
    void setProperties(Properties properties);

    TagType getTagType();

    void setTagType(TagType tagType);

    void invoke()throws Exception;


}
