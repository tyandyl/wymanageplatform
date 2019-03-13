package com.wy.manage.platform.core.parser.test;

import com.wy.manage.platform.core.model.CssModel;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.EdgeLine;
import com.wy.manage.platform.core.parser.NfaStateMachine;
import com.wy.manage.platform.core.parser.RegularExpressionParser;
import com.wy.manage.platform.core.utils.FileTools;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试[0-9]
 * Created by tianye
 */
public class Test1 {
    public static void main(String[] agrs) throws Exception {
        String gbk = "我来了";
        String utf8 = new String(gbk.getBytes("UTF-8"));

        //模拟UTF-8编码的网站显示
        System.out.println(new String(utf8.getBytes(),"UTF-8"));
//        String gbk = "今年是2011年";
//        String utf8 = new String(gbk.getBytes("UTF-8"));
//
//        //模拟UTF-8编码的网站显示
//        System.out.println(new String(utf8.getBytes(),"UTF-8"));
    }

    public static void analyze() throws Exception {
        String gbk = "我来了";
        String utf8 = new String(gbk.getBytes("UTF-8"));
        for (byte b : gbk.getBytes("UTF-8")) {
            System.out.print(b + " ");
        }
        System.out.println();
        for (byte b : utf8.getBytes()) {
            System.out.print(b + " ");
        }
    }
    /*
    -26 -120 -111 -26 -99 -91 -28 -70 -122
    -26 -120 -111 -26 -99 -91 -28 -70 63
    */
}
