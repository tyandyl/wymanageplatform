package com.wy.manage.platform.core.utils;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by tianye on 2018/9/6.
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
        int ch=0;
        //左大括号是否已经标记
        boolean leftBigBracketsIsMarked=false;
        StringBuffer line=new StringBuffer();
        while ((ch=fileReader.read())!=-1){
            switch (ch){
                case 13:
                    line.append("回车");
                    break;
                case 30:
                    line.append("空格");
                    break;
                case 47:
                    line.append("斜杠");
                    break;
                default:
                    line.append((char)ch);
                    break;
            }
            leftBigBracketsIsMarked=true;

        }
        System.out.println(line);
        return null;

    }

    private static String getConfigUrl() {
        return System.getProperty("wy.config");
    }
}
