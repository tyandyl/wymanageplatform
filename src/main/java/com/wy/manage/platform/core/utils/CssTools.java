package com.wy.manage.platform.core.utils;

import com.wy.manage.platform.core.parser.*;
import com.wy.manage.platform.core.widget.AbsoluteFlow;
import com.wy.manage.platform.core.widget.IFlow;
import com.wy.manage.platform.core.widget.NormalFlow;
import com.wy.manage.platform.core.widget.RelativeFlow;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by tianye
 */
public class CssTools {

    public static Map<String,Map<String,String>> loadProperties(String name) {
        Map<String,Map<String,String>> css=null;
        try {
            css=new HashMap<String, Map<String, String>>();
            String configUrl = getConfigUrl();
            if(StringUtils.isBlank(configUrl)){
                String curDir = System.getProperty("user.dir");
                StringBuffer curFullDir=new StringBuffer(curDir);
                curFullDir.append(File.separator);
                curFullDir.append("src");
                curFullDir.append(File.separator);
                curFullDir.append("main");
                curFullDir.append(File.separator);
                curFullDir.append("resources");
                curFullDir.append(File.separator);
                curFullDir.append("win");
                File root=new File(curFullDir.toString());
                File cssFile = new File(root, name);
                System.out.println(cssFile.getAbsolutePath());
                load( cssFile);
            }
        }catch (Exception e){

        }
        return css;

    }

    private static Map<String,Map<String,String>> load(File cssFile) throws IOException {
        FileReader fileReader=new FileReader(cssFile);

        return null;

    }

    public static NfaStateMachine parser()throws Exception{
        //定义css解析的正则表达式position:static
        NfaStateMachine invokeStatic = new InvokerImpl("static").relevance(new RelevanceHandle<IFlow>() {
            public IFlow handle() {
                return new NormalFlow();
            }
        }).setIsPrint(true).invoke();

        //position:relative
        NfaStateMachine invokeRelative = new InvokerImpl("relative").relevance(new RelevanceHandle<IFlow>() {
            public IFlow handle() {
                return new RelativeFlow();
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine invokeAbsolute = new InvokerImpl("absolute").relevance(new RelevanceHandle<IFlow>() {
            public IFlow handle() {
                return new AbsoluteFlow();
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine invokePosition = new InvokerImpl("position:").relevance(new RelevanceHandle<IFlow>() {
            public IFlow handle() {
                return new AbsoluteFlow();
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine orNfaStateMachine = NfaManager.createOrNfaStateMachine(invokeStatic, invokeRelative);

        NfaStateMachine orNfaStateMachine1 = NfaManager.createOrNfaStateMachine(orNfaStateMachine, invokeAbsolute);

        NfaStateMachine linkNfaStateMachine = NfaManager.createLinkNfaStateMachine(invokePosition, orNfaStateMachine1);
        //String str="#|.[^#.]+\\{";
        return linkNfaStateMachine;
    }

    private static String getConfigUrl() {
        return System.getProperty("wy.config");
    }
}
