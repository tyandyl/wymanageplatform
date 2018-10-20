package com.wy.manage.platform.core.utils;

import com.wy.manage.platform.core.parser.*;
import com.wy.manage.platform.core.widget.AbsoluteFlow;
import com.wy.manage.platform.core.widget.IFlow;
import com.wy.manage.platform.core.widget.NormalFlow;
import com.wy.manage.platform.core.widget.RelativeFlow;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.*;

/**
 * Created by tianye
 */
public class CssTools extends Properties{
    private static final long serialVersionUID = 9072867476967297899L;
    private static CssTools properties = null;
    private final LinkedHashSet<Object> keys = new LinkedHashSet<Object>();

    static {
        loadProperties();
    }

    private static Map<String,Map<String,String>> loadProperties() {
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
                curFullDir.append("regular");
                File root=new File(curFullDir.toString());
                File cssFile = new File(root, "css.properties");
                if (cssFile.exists()){
                    load(cssFile);
                }

            }
        }catch (Exception e){
            System.out.println(e);
        }
        return css;

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

    public static Properties getProperties() {
        return properties;
    }

    private static void load(File cssFile) throws IOException {
        properties = new CssTools();
        InputStream is = new FileInputStream(cssFile);
        properties.load(is);
    }

    public static NfaStateMachine parser()throws Exception{
        //定义css解析的正则表达式position:static
        NfaStateMachine invokeStatic = new InvokerImpl("static").relevance(new RelevanceHandle<IFlow>() {
            public void handle(ModelParam modelParam,IFlow iFlow) {
               // return new NormalFlow();
            }
        }).setIsPrint(true).invoke();

        //position:relative
        NfaStateMachine invokeRelative = new InvokerImpl("relative").relevance(new RelevanceHandle<IFlow>() {
            public void handle(ModelParam modelParam,IFlow iFlow) {
                //return new RelativeFlow();
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine invokeAbsolute = new InvokerImpl("absolute").relevance(new RelevanceHandle<IFlow>() {
            public void handle(ModelParam modelParam,IFlow iFlow) {
                //return new AbsoluteFlow();
            }
        }).setIsPrint(true).invoke();

        NfaStateMachine invokePosition = new InvokerImpl("position:").relevance(new RelevanceHandle<IFlow>() {
            public void handle(ModelParam modelParam,IFlow iFlow) {
                //return new AbsoluteFlow();
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
