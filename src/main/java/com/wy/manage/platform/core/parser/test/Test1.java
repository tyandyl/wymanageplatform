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
        cssModel.execute("#wintop{\n" +
                "position:absolute;\n" +
                "display:block;\n" +
                "top:0px;\n" +
                "left:0px;\n" +
                "right:0px;\n" +
                "height: 70px;\n" +
                "background: #0382AD url(winpic/r_x.png) repeat-x;}");
    }
}
