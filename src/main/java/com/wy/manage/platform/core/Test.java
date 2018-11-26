package com.wy.manage.platform.core;

import com.wy.manage.platform.core.model.CssModel;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.parser.*;

/**
 * Created by tianye on 2018/6/29.
 */
public class Test {
    public static void main(String[] agrs) throws Exception {
        HtmlModel cssModel=new HtmlModel();
        cssModel.defineAction();
        cssModel.execute("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta http-equiv=\"refresh\" content=\"text/html; charset=utf-8\">\n" +
                "    <script type=\"text/javascript\" src=\"jquery-1.6.2.min.js\"></script>\n" +
                "    <link rel=\"stylesheet\" style=\"text/css\" href=\"win.css\" />\n" +
                "</head>\n" +
                "</html>");
    }
}
