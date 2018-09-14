package com.wy.manage.platform.core.parser.test;

import com.wy.manage.platform.core.parser.EdgeLine;
import com.wy.manage.platform.core.parser.NfaStateMachine;
import com.wy.manage.platform.core.parser.RegularExpressionParser;

import java.util.List;

/**
 * 测试[0-9]
 * Created by tianye13 on 2018/9/12.
 */
public class Test1 {
    public static void main(String[] agrs) throws Exception {
        String m="[0-9a-z]";
        NfaStateMachine nfaStateMachine = RegularExpressionParser.parserCss(m);
        EdgeLine edgeLines = nfaStateMachine.getStartNode().getEdgeLines()[0];
        List<Character> edgeAllowInputGather = edgeLines.getEdgeAllowInputGather();
        if(edgeAllowInputGather.contains('b')){
            System.out.println("成功了");
        }
    }
}
