package com.wy.manage.platform.core;

import java.util.List;

/**
 * Created by tianye on 2018/6/28.
 */
public class Node implements IBlock {

    private Block block;

    public int getWidth() {
        return block.getWidth();
    }

    public void setWidth(int width) {
        block.setWidth(width);
    }

    public int getHeight() {
        return block.getHeight();
    }

    public void setHeight(int height) {
        block.setHeight(height);
    }

    public Flow getFlow() {
        return block.getFlow();
    }

    public void setFlow(Flow flow) {
        block.setFlow(flow);
    }

    public IBlock getParent() {
        return block.getParent();
    }

    public void setParent(IBlock block) {
        block.setParent(block);
    }

    public String getIdentifying() {
        return block.getIdentifying();
    }

    public void setIdentifying(String identifying) {
        block.setIdentifying(identifying);
    }

    public String getClassName() {
        return block.getClassName();
    }

    public void setClassName(String className) {
        block.setClassName(className);
    }

    public String getDescription() {
        return block.getDescription();
    }

    public void setDescription(String description) {
        block.setDescription(description);
    }

    public BlockType getBlockType() {
        return block.getBlockType();
    }

    public void setBlockType(BlockType blockType) {
        block.setBlockType(blockType);
    }

    public String getCode() {
        return block.getCode();
    }

    public void setCode(String code) {
        block.setCode(code);
    }

    public String getName() {
        return block.getName();
    }

    public void setName(String name) {
        block.setName(name);
    }

    public List<IBlock> getChildren() {
        return block.getChildren();
    }

    public void setChildren(List<IBlock> list) {
        block.setChildren(list);
    }

    public boolean isRoot() {
        return block.isRoot();
    }

    public void setRoot() {
        block.setRoot();
    }
}
