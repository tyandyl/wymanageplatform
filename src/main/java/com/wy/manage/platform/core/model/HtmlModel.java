package com.wy.manage.platform.core.model;

import com.wy.manage.platform.core.action.htmlAction.*;

/**
 * Created by tianye
 */
public class HtmlModel  extends BasicModel{
    public String getAddress() {
        return  "regular/html.properties";
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
    }
}
