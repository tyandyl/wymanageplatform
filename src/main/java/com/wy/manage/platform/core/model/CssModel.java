package com.wy.manage.platform.core.model;

import com.wy.manage.platform.core.action.cssAction.AttributeFirstLineAction;
import com.wy.manage.platform.core.action.cssAction.CssAttributeFullAction;

/**
 * Created by tianye
 */
public class CssModel<T> extends BasicModel<T>{
    @Override
    public String getAddress() {
        return "regular/css.properties";
    }
    @Override
    public void defineAction() {
        super.defineAction();
        defineAction(new AttributeFirstLineAction());
        defineAction(new CssAttributeFullAction());
    }


}
