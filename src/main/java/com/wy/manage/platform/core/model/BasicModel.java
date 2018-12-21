package com.wy.manage.platform.core.model;

import com.wy.manage.platform.core.action.Action;
import com.wy.manage.platform.core.action.CommonAction;
import com.wy.manage.platform.core.parser.*;
import com.wy.manage.platform.core.utils.PropertiesTools;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by tianye
 */
public abstract class BasicModel<T> implements Model<T>{
    private Map<String,Action> nameActions=new HashMap<String, Action>();
    private Map<String,Action> valueActions=new HashMap<String, Action>();
    private Map<String,String> nValue=new HashMap<String, String>();
    private String regular;

    public abstract String getAddress();

    public void defineAction() {
    }

    public void defineAction(Action action) {
        nameActions.put(action.getName(),action);
        List<String> groupNames = action.getIntraGroupNames();
        if(groupNames!=null && groupNames.size()>0){
            for(final String str:groupNames){
                if("borderStyleValue".equalsIgnoreCase(str)){
                    System.out.println();
                }
                if(nameActions.get(str)==null){
                    nameActions.put(str, new CommonAction() {
                        @Override
                        public String getName() {
                            return str;
                        }
                    });
                }
            }
        }
    }



    public void execute(String str,T t)throws Exception{
        String s = parserCompile();
        char[] chars1 = s.toCharArray();
        StringBuffer stringBuffer=new StringBuffer();
        for(int w=0;w<chars1.length;w++){
            if(chars1[w]==10){
                stringBuffer.append("\\n");
            }else if(chars1[w]==13) {
                stringBuffer.append("\\r");
            }else {
                stringBuffer.append(chars1[w]);
            }
        }
        System.out.println(stringBuffer.toString());
        Stack<XContentItem> parser = RegularExpressionParser.parser(chars1, this.valueActions,false);
        XContentItem peek = parser.peek();
        Set<Integer> index = peek.getIndex();
        int y=0;
        for(Integer i:index){
            if(y!=i){
                System.out.println("不相等,y是:"+y+",i是:"+i);
            }
            y++;
        }
        check( peek);
//        CssBag cssBag=new CssBag();
//        Page page=new Page();
        char[] chars = str.toCharArray();
        ModelParam<T> modelParam = new ModelParam<T>(t,chars);

        AnalyzeExecuteModel.execute(modelParam,peek.getNfaStateMachine());

    }

    public NfaStateMachine parserExplain()throws Exception {
        return null;
    }

    public String parserCompile() {
        String address = getAddress();
        PropertiesTools cssProperties = PropertiesTools.loadProperties(address);
        //重写，保证读取顺序
        Set<String> strings = cssProperties.stringPropertyNames();
        for (String name:strings) {
            //解决css中人为做出来的空格
            StringBuffer line=new StringBuffer();
            String value = cssProperties.getProperty(name);
            if (value != null) {
                if(value.contains(" ")){
                    String[] split = value.split(" ");
                    if(split!=null && split.length!=0){
                        for(int i=0;i<split.length;i++){
                            String contextValue = nValue.get(split[i]);
                            if(StringUtils.isNotBlank(contextValue)){
                                line.append(("("+contextValue+")"));
                            }else {
                                line.append(split[i]);

                            }
                        }
                        nValue.put(name,line.toString());
                        processAction( name, line.toString());
                        regular=line.toString();
                    }
                }else {
                    nValue.put(name,value);
                    processAction( name, value);
                    regular=value;
                }
            }

        }
        StringBuffer s=new StringBuffer();
        s.append("(");
        s.append(regular);
        s.append(")");
        return s.toString();
    }

    private void processAction(String name,String value){
        Action action = nameActions.get(name);
        if(action!=null){
            action.setValue(value);
            valueActions.put(value,action);
        }
    }

    private void check(XContentItem peek)throws Exception{
        System.out.println("-----------------------------------");
        System.out.println();
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
