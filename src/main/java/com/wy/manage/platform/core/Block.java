package com.wy.manage.platform.core;

/**
 * Created by tianye on 2018/6/27.
 */
public interface Block {

    String PIXEL_UNIT="px";

    /**
     * 获取块的宽度
     * @return
     */
    int getWidth();

    /**
     * 设置块的宽度
     */
    void setWidth(int width);

    /**
     * 获取块的高度
     * @return
     */
    int getHeight();

    /**
     * 设置块的高度
     */
    void setHeight(int height);

    /**
     * 获取流
     */
    int getFlow();


    /**
     *设置流
     */
    void setFlow(Flow flow);

    /**
     * 获取父类
     */
    Block getParentBlock();

    /**
     * 设置父类
     */
    void setParentBlock(Block block);


}
