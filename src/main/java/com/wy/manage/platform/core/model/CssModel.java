package com.wy.manage.platform.core.model;

import com.wy.manage.platform.core.action.cssAction.*;
import com.wy.manage.platform.core.action.cssAction.bg.BackgroundLineAction;
import com.wy.manage.platform.core.action.cssAction.border.BorderLineAction;
import com.wy.manage.platform.core.action.cssAction.dimension.DimensionAction;
import com.wy.manage.platform.core.action.cssAction.font.FontGroupLineAction;
import com.wy.manage.platform.core.action.cssAction.layout.LayoutLineAction;
import com.wy.manage.platform.core.action.cssAction.position.PositionLineAction;
import com.wy.manage.platform.core.action.cssAction.table.TableLineAction;
import com.wy.manage.platform.core.action.cssAction.text.TextLineAction;
import com.wy.manage.platform.core.action.cssAction.userinterface.UserInterfaceLineAction;

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
        defineAction(new BackgroundLineAction());
        defineAction(new BorderStyleLineAction());
        defineAction(new ColorLineAction());
        defineAction(new CursorLineAction());
        defineAction(new FontGroupLineAction());
        defineAction(new BorderLineAction());
        defineAction(new CssStopAction());
        defineAction(new DimensionAction());
        defineAction(new TextLineAction());
        defineAction(new UserInterfaceLineAction());
        defineAction(new TableLineAction());
    }


}
