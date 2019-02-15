package com.wy.manage.platform.core.widget;

/**
 * Created by tianye on 2018/6/28.
 * 块的类型
 */
public enum BlockType {
    TREE(1,"tree"),
    INPUT_TEXT(2,"input_text"),
    INPUT_BUTTON(3,"input_button"),
    NORMAL_DIV(4,"normal_div"),//一般的块
    NORMAL_BUTTON(5,"normal_button"),//一般的按钮
    ;

    private int code;
    private String name;

    BlockType(int code,String name){
        this.code=code;
        this.name=name;
    }

    public static BlockType getBlockType(int code){
        for (BlockType c : BlockType.values()) {
            if (c.getCode()==code) {
                return c;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
