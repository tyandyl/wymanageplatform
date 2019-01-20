package com.wy.manage.platform.core.model;

import com.wy.manage.platform.core.action.cssAction.*;
import com.wy.manage.platform.core.action.cssAction.bg.BackgroundLineAction;
import com.wy.manage.platform.core.action.cssAction.dimension.DimensionAction;
import com.wy.manage.platform.core.action.cssAction.font.FontGroupLineAction;
import com.wy.manage.platform.core.action.cssAction.layout.LayoutLineAction;

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
        defineAction(new PositionLineAction());
        defineAction(new LayoutLineAction());
        defineAction(new PxLineAction());
        defineAction(new BackgroundLineAction());
        defineAction(new BorderStyleLineAction());
        defineAction(new ColorLineAction());
        defineAction(new OverflowLineAction());
        defineAction(new CursorLineAction());
        defineAction(new FontGroupLineAction());
        defineAction(new BorderLineAction());
        defineAction(new CssStopAction());
        defineAction(new DimensionAction());
    }


}
