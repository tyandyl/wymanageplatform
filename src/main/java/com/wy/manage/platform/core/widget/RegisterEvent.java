package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.utils.ExceptionTools;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye13 on 2019/3/13.
 */
public class RegisterEvent {
    private static HashMap<String, String> eventMap = new HashMap<String, String>() {
        private static final long serialVersionUID = -7130991970747133460L;
        {
            put("search_button", "click;widget");
            put("window2-table-input","widget");
            //value为事件[辅助参数,辅助参数]
            put("window2-combo-list-cell-after", "dropDown:window2-combo-list-cell,window2-combo-list-cell_real");
            put("Window2","widget");
            put("window2-combo-list-div","widget");
            put("divSet","widget");
            put("selectMultipleLeft","selectShowByPage");
            put("addOneWY","click:selectMultipleLeft,selectMultipleRight");
            //click 注册按钮点击事件，后边跟着要获取值的参数
            put("buttonSaveByButton", "widget;requestClick:urlSetInputView,targetSetInputView,selectMultipleRight");
            put("window2-closed","closed:Window2");
        }
    };

    private static HashMap<String, Integer> WidgetMap = new HashMap<String, Integer>() {
        private static final long serialVersionUID = -7130991970747133460L;
        {
            put("window2-table-input",6);
            put("search_button",3);
            put("divSet",7);
        }
    };


    //辅助参数指向数组
    private Map<String,List<RegisterEventData>> maps=new HashMap<String, List<RegisterEventData>>();
    //主参数指向数组所在类
    private Map<String,List<RegisterEventManage>> mapManage=new HashMap<String, List<RegisterEventManage>>();

    public RegisterEvent handle()throws Exception{
        if(mapManage.size()==0){
            for(Map.Entry<String,String> entry:eventMap.entrySet()){
                String value = entry.getValue();
                //每个事件总结
                if(value.contains(";")){
                    String[] split = value.split(";");
                    for(int i=0;i<split.length;i++){
                        RegisterEventManage manage=new RegisterEventManage();
                        initRegisterEventManage( entry.getKey(), split[i],  manage, maps);
                        List<RegisterEventManage> registerEventManages = mapManage.get(entry.getKey());
                        if(registerEventManages==null ||registerEventManages.size()==0){
                            registerEventManages=new ArrayList<RegisterEventManage>();
                        }
                        registerEventManages.add(manage);
                        mapManage.put( entry.getKey(),registerEventManages);
                    }
                }else {
                    RegisterEventManage manage=new RegisterEventManage();
                    initRegisterEventManage( entry.getKey(), value,  manage, maps);
                    List<RegisterEventManage> registerEventManages = mapManage.get(entry.getKey());
                    if(registerEventManages==null ||registerEventManages.size()==0){
                        registerEventManages=new ArrayList<RegisterEventManage>();
                    }
                    registerEventManages.add(manage);
                    mapManage.put( entry.getKey(),registerEventManages);
                }

            }

        }
        return this;

    }






    public static void initRegisterEventManage(String key,String value, RegisterEventManage manage,Map<String,List<RegisterEventData>> maps)throws Exception{
        if(value.contains(":")){
            String[] split = value.split(":");
            if(split.length>1&& StringUtils.isNotBlank(split[1])){
                if(split[1].contains(",")){
                    String[] split1 = split[1].split(",");
                    if(split1.length>3){
                        ExceptionTools.ThrowException("辅助参数个数不能大于3");
                    }
                    manage.setParamNum(split1.length);
                    manage.setSelectorValue(key);
                    manage.getArr()[0]=split[0];
                    for(int y=0;y<split1.length;y++){
                        List<RegisterEventData> registerEventDatas = maps.get(split1[y]);
                        if(registerEventDatas!=null){
                            registerEventDatas.add(new RegisterEventData(key,y+1));
                        }else {
                            registerEventDatas=new ArrayList<RegisterEventData>();
                            registerEventDatas.add(new RegisterEventData(key,y+1));
                        }
                        maps.put(split1[y],registerEventDatas);
                    }
                }else {
                    manage.getArr()[0]=split[0];
                    manage.setSelectorValue(key);
                    manage.setParamNum(1);
                    List<RegisterEventData> registerEventDatas = maps.get(split[1]);
                    if(registerEventDatas!=null){
                        registerEventDatas.add(new RegisterEventData(key,1));
                    }else {
                        registerEventDatas=new ArrayList<RegisterEventData>();
                        registerEventDatas.add(new RegisterEventData(key,1));
                    }
                    maps.put(split[1],registerEventDatas);
                }
            }else {
                manage.setSelectorValue(key);
                manage.getArr()[0]=split[0];
            }
        }else {
            manage.setSelectorValue(key);
            manage.getArr()[0]=value;
        }
    }

    public static HashMap<String, String> getEventMap() {
        return eventMap;
    }

    public static void setEventMap(HashMap<String, String> eventMap) {
        RegisterEvent.eventMap = eventMap;
    }

    public Map<String, List<RegisterEventData>> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, List<RegisterEventData>> maps) {
        this.maps = maps;
    }

    public Map<String, List<RegisterEventManage>> getMapManage() {
        return mapManage;
    }

    public void setMapManage(Map<String, List<RegisterEventManage>> mapManage) {
        this.mapManage = mapManage;
    }

    public static HashMap<String, Integer> getWidgetMap() {
        return WidgetMap;
    }

    public static void setWidgetMap(HashMap<String, Integer> widgetMap) {
        WidgetMap = widgetMap;
    }
}
