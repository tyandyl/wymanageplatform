package com.wy.manage.platform.core.widget;

import com.wy.manage.platform.core.parser.CssBag;

import java.util.List;
import java.util.Map;

/**
 * Created by tianye on 2018/7/1.
 */
public class WidgetFactory {
    public static Widget getWidget(Page page,String selectorType,String selectorValue)throws Exception{
        Widget widget=new Widget();
        Map<String, List<CssBag>> cssMaps = page.getCssMaps();
        for(Map.Entry<String,List<CssBag>> entry:cssMaps.entrySet()){
            if(entry.getValue()!=null){
            }
        }
        return null;
    }
}
