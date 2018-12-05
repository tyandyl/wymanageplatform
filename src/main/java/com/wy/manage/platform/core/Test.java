package com.wy.manage.platform.core;

import com.wy.manage.platform.core.model.CssModel;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.parser.*;
import com.wy.manage.platform.core.widget.Link;
import com.wy.manage.platform.core.widget.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye on 2018/6/29.
 */
public class Test {
    public static void main(String[] agrs) throws Exception {
        HtmlModel<Page> htmlModel=new HtmlModel<Page>();
        htmlModel.defineAction();
        Page page=new Page();
        htmlModel.execute("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> \n" +
                "<script type=\"text/javascript\" src=\"jquery-1.6.2.min.js\"></script>\n" +
                "<script src=\"win.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"./tree/jquery.tree.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"./contextmenu/contextmenu.js\"></script>\n" +
                "<link rel=\"stylesheet\" style=\"text/css\" href=\"win.css\" />\n" +
                "\n" +
                "<link rel=\"stylesheet\" style=\"text/css\" href=\"./tree/tree.css\" />\n" +
                "<link rel=\"stylesheet\" style=\"text/css\" href=\"./contextmenu/contextmenu.css\" />" +
                "</head>\n" +
                "</html>",page);
        Link link = page.getLinks().get(0);
        final String href = link.getHref();
        System.out.println("css的地址是:"+href);
        CssModel<List<CssBag>> cssModel=new CssModel<List<CssBag>>();
        cssModel.defineAction();
        List<CssBag> css=new ArrayList<CssBag>();
        cssModel.execute("#win-screen{\n" +
                "position:absolute;\n" +
                "display:block;\n" +
                "top:0px;\n" +
                "left:0px;\n" +
                "right:0px;\n" +
                "bottom:0px;\n" +
                "}",css);
    }
}
