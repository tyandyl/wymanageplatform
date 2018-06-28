package com.wy.manage.platform.core;

import java.util.List;

/**
 * Created by tianye on 2018/6/28.
 */
public class Block implements IBlock {

    private boolean isRoot;
    private Flow flow;
    private int width;
    private int height;
    private IBlock parent;
    private String identifying;
    private String className;
    private String description;

    private BlockType blockType;
    private String code;
    private String name;

    private List<IBlock> children;

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot() {
         isRoot=true;
    }


    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public IBlock getParent() {
        return parent;
    }

    public void setParent(IBlock parent) {
        this.parent = parent;
    }

    public String getIdentifying() {
        return identifying;
    }

    public void setIdentifying(String identifying) {
        this.identifying = identifying;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockType blockType) {
        this.blockType = blockType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IBlock> getChildren() {
        return children;
    }

    public void setChildren(List<IBlock> children) {
        this.children = children;
    }
}
