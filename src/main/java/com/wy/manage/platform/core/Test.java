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
                "}\n" +
                 "#search_button {\n" +
                        "border: 1px solid #0382AD;\n" +
                        "background-color: #45B5DA;\n" +
                        "background: -webkit-linear-gradient(#45B5DA,#0382AD);\n" +
                        "background: linear-gradient(#45B5DA,#0382AD);\n" +
                        "overflow: visible;\n" +
                        "border-radius: 3px;\n" +
                        "cursor: pointer;\n" +
                        "font: 13px/1.7 verdana,'\\5b8b\\4f53',arial,georgia,helvetica,sans-serif;\n" +
                        "color: #FFFFFF;\n" +
                        "width:50px;\n" +
                        "}\n"+
                "#win-top{\n" +
                "position:absolute;\n" +
                "display:block;\n" +
                "top:0px;\n" +
                "left:0px;\n" +
                "right:0px;\n" +
                "height: 70px;\n" +
                "background: #0382AD url(winpic/r_x.png) repeat-x;\n" +
                "background: -moz-linear-gradient(center top,#45B5DA,#0382AD);\n" +
                "background: -webkit-gradient(linear,center top,center bottom,from(#45B5DA),to(#0382AD));\n" +
                "}\n" +
                "#win-bottom{\n" +
                "position:absolute;\n" +
                "display:block;\n" +
                "left:0px;\n" +
                "right:0px;\n" +
                "bottom:0px;\n" +
                "height: 10px;\n" +
                "background: #0382AD url(winpic/r_x.png) repeat-x;\n" +
                "background: -moz-linear-gradient(center top,#45B5DA,#0382AD);\n" +
                "background: -webkit-gradient(linear,center top,center bottom,from(#45B5DA),to(#0382AD));\n" +
                "}\n" +
                "#win-left{\n" +
                "position:absolute;\n" +
                "display:block;\n" +
                "top:80px;\n" +
                "left:10px;\n" +
                "bottom:0px;\n" +
                "width:250px;\n" +
                "border-width:1px;\n" +
                "border-style:solid;\n" +
                "border-color:#4DABC9;\n" +
                "}\n" +
                "#search_text{\n" +
                "position:absolute;\n" +
                "display:inline;\n" +
                "top:5px;\n" +
                "left:5px;\n" +
                "right:0px;\n" +
                "}\n" +
                "#search_input{\n" +
                "border-width:1px;\n" +
                "border-style:solid;\n" +
                "border-color:#4DABC9;\n" +
                "width:180px;\n" +
                "height:20px;\n" +
                "}",css);
    }
}
