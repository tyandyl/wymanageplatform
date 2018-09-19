package com.wy.manage.platform.core.utils;

import com.wy.manage.platform.core.parser.EdgeLine;
import com.wy.manage.platform.core.parser.NfaManager;
import com.wy.manage.platform.core.parser.NfaStateMachine;
import com.wy.manage.platform.core.parser.NfaStateNode;
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
        fileReader
        return null;

    }

    public NfaStateMachine parser()throws Exception{
        return null;
    }

    private static String getConfigUrl() {
        return System.getProperty("wy.config");
    }
}
