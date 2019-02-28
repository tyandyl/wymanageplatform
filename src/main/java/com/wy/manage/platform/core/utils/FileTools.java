package com.wy.manage.platform.core.utils;

import com.wy.manage.platform.core.widget.Page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;

/**
 * Created by tianye
 */
public class FileTools {

    public static StringBuffer getContent(String address,boolean isOut)throws Exception{
        URL resource = FileTools.class.getClassLoader().getResource(address);
        File file=new File(resource.toURI());

        Reader fr = new FileReader(file);
        BufferedReader bufr = new BufferedReader(fr);
        StringBuffer stringBuffer=new StringBuffer();
        String line = null;
        boolean isInvalid=false;
        while((line = bufr.readLine())!=null) {
            if(isOut && (line.contains("/**") || line.contains("/*"))){
                isInvalid=true;
            }

            if(isOut && !isInvalid){
                stringBuffer.append(line);
            }
            if(!isOut){
                stringBuffer.append(line+"\n");
            }
            if(isOut && (line.contains("**/") || line.contains("*/"))){
                isInvalid=false;
            }

        }
        return stringBuffer;
    }
}
