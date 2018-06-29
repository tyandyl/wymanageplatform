package com.wy.manage.platform.core;

import java.util.List;

/**
 * Created by tianye on 2018/6/29.
 */
public interface IWidget extends IBlock{

    /**
     * 获取块的宽度
     * @return
     */
    int getWidth() throws Exception;

    /**
     * 设置块的宽度
     * @param width
     */
    void setWidth(int width) throws Exception;

    /**
     * 获取块的高度
     * @return
     */
    int getHeight() throws Exception;

    /**
     * 设置块的高度
     * @param height
     */
    void setHeight(int height) throws Exception;

    /**
     * 获取流
     * @return
     */
    IFlow getFlow();

    /**
     * 设置流
     * @param flow
     */
    void setFlow(IFlow flow);



    /**
     * 获取ID列表，这是和class差不多的id
     * @return
     */
    List<Property> getIdentifying();


    /**
     * 设置ID
     * @param iProperty
     */
    void addIdentifying(Property iProperty);

    /**
     * 获取class列表
     * @return
     */
    List<Property> getClassList();


    /**
     * 增加一个class
     * @param iProperty
     */
    void addClass(Property iProperty);


    /**
     * 获得描述
     * @return
     */
    String getDescription();

    /**
     * 设置描述
     * @param description
     */
    void setDescription(String description);

    /**
     * 获取类型
     * @return
     */
    BlockType getBlockType();

    /**
     * 设置类型
     * @param blockType
     */
    void setBlockType(BlockType blockType);

    /**
     * 设置名称，对于具体的控件有具体的作用
     * @return
     */
    String getTitle();

    /**
     * 设置名称
     * @param name
     */
    void setTitle(String name);

}
