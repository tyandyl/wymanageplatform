package com.wy.manage.platform.core;

import java.util.List;

/**
 * Created by tianye on 2018/6/28.
 * 兼容性
 */
public interface ICompatibility {
    /**
     * 获取浏览器兼容列表
     * @return
     */
    List<String> getCompatibility();

    /**
     * 设置浏览器兼容性
     */
    void setCompatibility(List<String> compatibility);


}
