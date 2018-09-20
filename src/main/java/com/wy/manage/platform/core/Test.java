package com.wy.manage.platform.core;

import com.wy.manage.platform.core.attribute.AttributeNameType;
import com.wy.manage.platform.core.parser.*;
import com.wy.manage.platform.core.utils.CssTools;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.Widget;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by tianye on 2018/6/29.
 */
public class Test {
    public static void main(String[] agrs) throws Exception {
        NfaStateMachine parser = CssTools.parser();
        AnalyzeExecuteModel.execute("position:relative",parser);

    }
}
