package com.wy.manage.platform.core.model;

import com.wy.manage.platform.core.action.htmlAction.*;
import com.wy.manage.platform.core.action.htmlAction.link.LinkLineAction;
import com.wy.manage.platform.core.action.htmlAction.link.LinkLinesAction;
import com.wy.manage.platform.core.action.htmlAction.meta.MetaLineAction;
import com.wy.manage.platform.core.action.htmlAction.meta.MetaLinesAction;
import com.wy.manage.platform.core.action.htmlAction.node.*;
import com.wy.manage.platform.core.action.htmlAction.script.ScriptLineAction;
import com.wy.manage.platform.core.action.htmlAction.script.ScriptLinesAction;

/**
 * Created by tianye
 */
public class HtmlModel<T>  extends BasicModel<T>{
    public String getRegularAddress() {
        return "regular/html.properties";
    }

    @Override
    public String getContentAddress() {
        return null;
    }

    @Override
    public void defineAction() {
        super.defineAction();
        defineAction(new FirstLineAction());
        defineAction(new HeadStartTagAction());
        defineAction(new HeadEndTagAction());
        defineAction(new HtmlStartTagAction());
        defineAction(new HtmlEndTagAction());
        defineAction(new LinkLineAction());
        defineAction(new MetaLineAction());
        defineAction(new ScriptLineAction());
        defineAction(new MetaLinesAction());
        defineAction(new ScriptLinesAction());
        defineAction(new LinkLinesAction());
        defineAction(new DivStartLineAction());
        defineAction(new BodyEndLineAction());
        defineAction(new DivEndLineAction());
        defineAction(new BodyStartLineAction());
        defineAction(new ButtonStartLineAction());
        defineAction(new ButtonEndLineAction());
        defineAction(new TableStartLineAction());
        defineAction(new TableEndLineAction());
        defineAction(new TrStartLineAction());
        defineAction(new TrEndLineAction());
        defineAction(new TdStartLineAction());
        defineAction(new TdEndLineAction());
        defineAction(new InputLineAction());
        defineAction(new LegendStartLineAction());
        defineAction(new LegendEndLineAction());
        defineAction(new FormStartLineAction());
        defineAction(new FormEndLineAction());
        defineAction(new FieldsetStartLineAction());
        defineAction(new FieldsetEndLineAction());
    }
}
