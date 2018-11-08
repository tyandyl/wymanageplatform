package com.wy.manage.platform.core.model;

import com.wy.manage.platform.core.action.Action;
import com.wy.manage.platform.core.parser.*;
import com.wy.manage.platform.core.utils.CssProperties;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by tianye
 */
public abstract class BasicModel implements Model{
    private Map<String,Action> nameActions=new HashMap<String, Action>();
    private Map<String,Action> valueActions=new HashMap<String, Action>();
    private Map<String,String> nValue=new HashMap<String, String>();
    private String regular;

    public abstract String getAddress();

    public void defineAction() {
    }

    public void defineAction(Action action) {
        nameActions.put(action.getName(),action);
    }



    public void execute(String str)throws Exception{
        String s = parserCompile();
        Stack<XContentItem> parser = RegularExpressionParser.parser(s.toCharArray(), this.valueActions,false);
        XContentItem peek = parser.peek();
        Set<Integer> index = peek.getIndex();
        int y=0;
        for(Integer i:index){
            if(y!=i){
                System.out.println("不相等,y是:"+y+",i是:"+i);
            }
            y++;
        }
        CssBag cssBag=new CssBag();
        AnalyzeExecuteModel.execute(str,cssBag,peek.getNfaStateMachine());

    }

    public NfaStateMachine parserExplain()throws Exception {
        return null;
    }

    public String parserCompile() {
        String address = getAddress();
        CssProperties cssProperties = CssProperties.loadProperties(address);
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
                                if(split[i].contains("*")){
                                    line.append(("(("+contextValue+")*)"));
                                }else {
                                    line.append(("("+contextValue+")"));
                                }

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

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
