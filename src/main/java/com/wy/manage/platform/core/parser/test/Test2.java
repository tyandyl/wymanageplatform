package com.wy.manage.platform.core.parser.test;

import com.wy.manage.platform.core.action.Action;
import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.model.CssModel;
import com.wy.manage.platform.core.parser.*;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 测试6{2,5}
 * Created by tianye
 */
public class Test2 {
    public static void main(String[] agrs) throws Exception {
        Stack<XContentItem> parser = RegularExpressionParser.parser("[0-9]?y".toCharArray(), new HashMap<String, Action>(),false);
        System.out.println(parser.size());
        CssBag cssBag=new CssBag();
    }



}


