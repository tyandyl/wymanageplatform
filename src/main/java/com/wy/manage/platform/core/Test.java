package com.wy.manage.platform.core;

import com.wy.manage.platform.core.model.CssModel;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.parser.*;
import com.wy.manage.platform.core.utils.ChinaFontTools;
import com.wy.manage.platform.core.widget.Link;
import com.wy.manage.platform.core.widget.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye on 2018/6/29.
 */
public class Test {
    public static void main(String[] agrs) throws Exception {
        String mm=new String("宋".getBytes("utf-8"));
        System.out.println(mm);
       //ChinaFontTools.testCode("宋");
    }
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();

        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]).toLowerCase();
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }
}
