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
        int num=(12700/1000);
        int h=12700%1000;
        System.out.println(num);
        System.out.println(h);
    }
}
