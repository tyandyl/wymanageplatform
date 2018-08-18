package com.wy.manage.platform.core.widget;

import java.util.List;

/**
 * Created by tianye on 2018/6/29.
 */
public interface IWidget extends IBlock {

    /**
     * 获取块的宽度
     * @return
     */
    String getWidth() throws Exception;

    /**
     * 设置块的宽度
     * @param var
     */
    void setWidth(String var) throws Exception;

    /**
     * 获取块的高度
     * @return
     */
    String getHeight() throws Exception;

    /**
     * 设置块的高度
     * @param var
     */
    void setHeight(String var) throws Exception;

    /**
     * 获取流
     * @return
     */
    IFlow getFlow();

    /**
     * 设置流
     * @param var
     */
    void setFlow(IFlow var);

    /**
     * 获得描述
     * @return
     */
    String getDescription();

    /**
     * 设置描述
     * @param var
     */
    void setDescription(String var);

    /**
     * 获取类型
     * @return
     */
    BlockType getBlockType();

    /**
     * 设置类型
     * @param var
     */
    void setBlockType(BlockType var);

    /**
     * 设置名称，对于具体的控件有具体的作用
     * @return
     */
    String getTitle();

    /**
     * 设置名称
     * @param var
     */
    void setTitle(String var);

    List<String> getIds();

    void setIds(List<String> list);

    List<String> getClasses();

    void setClasses(List<String> list);





}
