package com.wy.manage.platform.core;

import java.util.List;

/**
 * Created by tianye on 2018/6/27.
 */
public interface IBlock {

    String PIXEL_UNIT="px";

    /**
     * 获取块的宽度
     */
    int getWidth();

    /**
     * 设置块的宽度
     * @param width
     */
    void setWidth(int width);

    /**
     * 获取块的高度
     */
    int getHeight();

    /**
     * 设置块的高度
     * @param height
     */
    void setHeight(int height);

    /**
     * 获取流
     */
    Flow getFlow();

    /**
     * 设置流
     * @param flow
     */
    void setFlow(Flow flow);

    /**
     * 获取父类
     */
    IBlock getParent();

    /**
     * 设置父类
     * @param block
     */
    void setParent(IBlock block);

    /**
     * 获取ID
     */
    String getIdentifying();


    /**
     * 设置ID
     * @param identifying
     */
    void setIdentifying(String identifying);

    /**
     * 获取class名称
     * @return
     */
    String getClassName();


    /**
     * 设置class名称
     * @param className
     */
    void setClassName(String className);


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
     * 获取编码，这里的编码采用tree型设计，以空间换取时间
     * @return
     */
    String getCode();

    /**
     * 设置编码
     * @param code
     */
    void setCode(String code);

    /**
     * 设置名称，对于具体的控件有具体的作用
     * @return
     */
    String getName();

    void setName(String name);

    List<IBlock> getChildren();

    void setChildren(List<IBlock> list);

    boolean isRoot();

    void setRoot();



}
