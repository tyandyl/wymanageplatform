package com.wy.manage.platform.core;

import com.wy.manage.platform.core.parser.*;

/**
 * Created by tianye on 2018/6/29.
 */
public class Test {
    public static void main(String[] agrs) throws Exception {
        NfaStateMachine parser = CssParser.parser();
        CssBag cssBag=new CssBag();
        AnalyzeExecuteModel.execute(" #sh",cssBag,parser);
    }
}
