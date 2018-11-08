package com.wy.manage.platform.core.parser.test;

import com.wy.manage.platform.core.model.CssModel;
import com.wy.manage.platform.core.parser.EdgeLine;
import com.wy.manage.platform.core.parser.NfaStateMachine;
import com.wy.manage.platform.core.parser.RegularExpressionParser;

import java.util.List;

/**
 * 测试[0-9]
 * Created by tianye
 */
public class Test1 {
    public static void main(String[] agrs) throws Exception {
        CssModel cssModel=new CssModel();
        cssModel.defineAction();
        cssModel.execute(".sh{ position : absolute;" +
                " \n display:block;" +
                "\n top:10px;"+
                "\n right:20px;"+
                "\n bottom:20px;"+
                "\n left:30px;}");
    }
}
