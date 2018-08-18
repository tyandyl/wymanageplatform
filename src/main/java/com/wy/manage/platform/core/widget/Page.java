package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.attribute.Properties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tianye on 2018/6/28.
 */
public class Page implements IBlock, Serializable {

    private static final long serialVersionUID = -2602415919744812735L;
    private String preposition;

    private List<Meta> metas;

    private List<Script> scripts;

    private List<Link> links;

    public void invoke(){

    }



    public Properties getProperties() {
        return null;
    }

    public void setProperties(Properties properties) {

    }

    public TagType getTagType() {
        return null;
    }

    public void setTagType(TagType tagType) {

    }

    public List<IBlock> getChildren() {
        return null;
    }

    public void setChildren(List<IBlock> list) {

    }
}
