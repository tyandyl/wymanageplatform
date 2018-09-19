package com.wy.manage.platform.core.parser;

/**
 * Created by tianye.
 */
public enum EdgeType {
    PASSED(1,"已经过"),
    NO_PASSED(2,"没有经过"),
    MAYBE(3,"随便");

    public int state;
    public String name;

    private EdgeType(int state, String name){
        this.state=state;
        this.name=name;
    }

    public static EdgeType findEdgeNum(int code) {
        EdgeType[] values = EdgeType.values();
        for (EdgeType each : values) {
            if (each.getState() == code) {
                return each;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
