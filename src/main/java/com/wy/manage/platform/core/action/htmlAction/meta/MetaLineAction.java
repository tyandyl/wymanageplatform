package com.wy.manage.platform.core.action.htmlAction.meta;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.IgnoreTools;
import com.wy.manage.platform.core.utils.TempTools;
import com.wy.manage.platform.core.widget.Meta;
import com.wy.manage.platform.core.widget.Page;
import com.wy.manage.platform.core.widget.WidgetModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class MetaLineAction extends BasicAction{

    private static final String HTTP_EQUIV_VALUE="httpEquivValue";

    private static final String META_CONTENT_VALUE="metaContentValue";

    @Override
    public void action(ModelParam modelParam) {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null){
                Meta meta=new Meta();
                StringBuffer httpEquivValue = (StringBuffer)regularValue.get(HTTP_EQUIV_VALUE);
                if(httpEquivValue!=null){
                    meta.setHttp_equiv(httpEquivValue.toString());
                }
                StringBuffer metaContentValue = (StringBuffer)regularValue.get(META_CONTENT_VALUE);
                if(metaContentValue!=null){
                    meta.setContent(metaContentValue.toString());
                }
                model.getPage().addMeta(meta);

            }
        }

    }
    @Override
    public String getName() {
        return "metaLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList(
                HTTP_EQUIV_VALUE,
                META_CONTENT_VALUE,
                this.getName());
        return list;
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
