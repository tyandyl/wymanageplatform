package com.wy.manage.platform.core.model;

import com.wy.manage.platform.core.action.*;

/**
 * Created by tianye
 */
public class CssModel extends BasicModel{
    @Override
    public String getAddress() {
        return "regular/css.properties";
    }
    @Override
    public void defineAction() {
        super.defineAction();
        defineAction(new AttributeTagAction());
        defineAction(new AttributeFirstLineAction());
        defineAction(new CssAttributeFullAction());
    }


}