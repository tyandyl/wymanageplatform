package com.wy.manage.platform.core.action.htmlAction.node;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.bean.*;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.parser.test.Encoding;
import com.wy.manage.platform.core.utils.ChinaFontTools;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.TempTools;
import com.wy.manage.platform.core.widget.*;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by tianye
 */
public class TdEndLineAction extends BasicAction {
    private static final String CHINESE_FONTS="ChineseFonts";
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            WidgetNodeTree widgetNodeTree = model.getPage().getWidgetNodeTree();

            Map regularValue = modelParam.getRegularValue();
            //不确定的正则表达式放开头不放结尾
            Object chineseFonts = regularValue.get(CHINESE_FONTS);
            String s =null;
            if(chineseFonts!=null){
                List<CurWidget> curWidgets=null;
                Object result = model.getParamResult().getResult().getResult();
                if(result instanceof List){
                    curWidgets = ((List<CurWidget>)result);
                }else {
                    ExceptionTools.ThrowException("报错,td结束不能为空");
                }
                CurWidget curWidget = curWidgets.get(curWidgets.size() - 1);
                s = ChinaFontTools.decodeUnicode(String.valueOf(chineseFonts));
                //很奇怪，一个汉字：宋就乱码，两个汉字就不乱码
                //System.out.println(Encoding.getEncoding(s));
                //获取系统默认编码
                //System.out.println("系统默认编码：" + System.getProperty("file.encoding")); //查询结果GBK
                //系统默认字符编码
                //System.out.println("系统默认字符编码：" + Charset.defaultCharset()); //查询结果GBK
                //操作系统用户使用的语言
                //System.out.println("系统默认语言：" + System.getProperty("user.language")); //查询结果zh
                curWidget.setOutContentValue(s);

            }
            //闭环校验，校验一些div名称之类的，目前先不校验
            WidgetNode pop = widgetNodeTree.getNewestNoClosed().pop();
            WidgetFactory.handleEvent(model);

            pop.getData().setOutValue(s);
        }
    }

    @Override
    public String getName() {
        return "tdEndLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList(CHINESE_FONTS);
        return list;
    }

    @Override
    public int getPriority() {
        return 1;
    }
}



