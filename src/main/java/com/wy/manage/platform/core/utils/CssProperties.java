package com.wy.manage.platform.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by tianye
 */
public class CssProperties extends Properties {
    private static final long serialVersionUID = -2296557505185513214L;
    private static CssProperties properties = null;
    private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

    public static CssProperties loadProperties(String address) {
        Map<String,Map<String,String>> css=null;
        try {
            String curDir = System.getProperty("user.dir");
            StringBuffer curFullDir=new StringBuffer(curDir);
            curFullDir.append(File.separator);
            curFullDir.append("src");
            curFullDir.append(File.separator);
            curFullDir.append("main");
            curFullDir.append(File.separator);
            curFullDir.append("resources");
            curFullDir.append(File.separator);
            curFullDir.append(address.split("/")[0]);
            curFullDir.append(File.separator);
            curFullDir.append(address.split("/")[1]);
            File root=new File(curFullDir.toString());
            File cssFile = new File(root, address.split("/")[2]);
            if (cssFile.exists()){
                load(cssFile);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return properties;

    }
    public Enumeration<Object> keys() {
        return Collections.<Object> enumeration(keys);
    }

    public Object put(Object key, Object value) {
        keys.add(key);
        return super.put(key, value);
    }

    public Set<Object> keySet() {
        return keys;
    }

    public Set<String> stringPropertyNames() {
        Set<String> set = new LinkedHashSet<String>();

        for (Object key : this.keys) {
            set.add((String) key);
        }

        return set;
    }

    private static void load(File cssFile) throws IOException {
        properties = new CssProperties();
        InputStream is = new FileInputStream(cssFile);
        properties.load(is);
    }
}
