package com.wy.manage.platform.core.attribute;

import java.util.List;

/**
 * Created by tianye on 2018/6/28.
 * 兼容性
 */
public interface ICompatibilities {
    /**
     * 获取浏览器兼容列表
     * @return
     */
    List<String> getCompatibilities();

    /**
     * 设置属性名浏览器兼容性
     */
    void setCompatibilities(List<String> compatibilities);

    String getPropertyType();




}
