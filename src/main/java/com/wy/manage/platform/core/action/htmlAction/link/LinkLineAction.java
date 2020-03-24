package com.wy.manage.platform.core.action.htmlAction.link;

import com.wy.manage.platform.core.action.BasicAction;
import com.wy.manage.platform.core.model.CssModel;
import com.wy.manage.platform.core.parser.CssBag;
import com.wy.manage.platform.core.parser.ModelParam;
import com.wy.manage.platform.core.utils.ExceptionTools;
import com.wy.manage.platform.core.utils.FileTools;
import com.wy.manage.platform.core.utils.PropertiesTools;
import com.wy.manage.platform.core.utils.TempTools;
import com.wy.manage.platform.core.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianye
 */
public class LinkLineAction extends BasicAction{

    private static final String LINK_REL_VALUE="linkRelValue";
    private static final String LINK_STYLE_VALUE="linkStyleValue";
    private static final String LINK_HREF="linkHref";
    @Override
    public void action(ModelParam modelParam)throws Exception {
        Object t = modelParam.getT();
        if(t instanceof WidgetModel) {
            WidgetModel model = (WidgetModel) t;
            Map regularValue = modelParam.getRegularValue();
            if(regularValue!=null && regularValue.get(this.getName())!=null){
                Link link=new Link();
                link.setRel(regularValue.get(LINK_REL_VALUE)!=null?((StringBuffer)regularValue.get(LINK_REL_VALUE)).toString():null);
                link.setStyle( regularValue.get(LINK_STYLE_VALUE)!=null? ((StringBuffer)regularValue.get(LINK_STYLE_VALUE)).toString():null);
                link.setHref(regularValue.get(LINK_HREF)!=null?((StringBuffer)regularValue.get(LINK_HREF)).toString():null);
                System.out.println("css的地址是:"+link.getHref());
                handleLink( model, link);

            }
        }

    }

    public void handleLink(WidgetModel model,Link link)throws Exception{
        HandleType addType = model.getParamResult().getHandleType();
        PropertiesTools propertiesTools = PropertiesTools.loadProperties("style.properties");
        String styleType = (String)(propertiesTools.get(link.getHref()));
        if(styleType==null){
            ExceptionTools.ThrowException("styleType不允许没值");
        }

        //代表使用外部链接方式
        if("1".equalsIgnoreCase(styleType)){
            if(addType== HandleType.NEW_PAGE){
                //代表使用外部链接方式
                if("1".equalsIgnoreCase(styleType)){
                    List<Link> links = model.getPage().getLinks();
                    links.add(link);
                    //内嵌到元素上
                }else if("3".equalsIgnoreCase(styleType)){
                    loadCss( model, link);
                }
            }else{
                ExceptionTools.ThrowException("新增css不允许使用外部链接");
            }
            //内嵌到元素上
        }else if("3".equalsIgnoreCase(styleType)){
            loadCss( model, link);
        }


    }

    @Override
    public String getName() {
        return "linkLine";
    }

    @Override
    public List<String> getIntraGroupNames() {
        List<String> list = TempTools.createList(
                LINK_REL_VALUE,
                LINK_STYLE_VALUE,
                LINK_HREF,
                this.getName());
        return list;
    }

    @Override
    public int getPriority() {
        return 2;
    }


    public void loadCss(WidgetModel model,Link link)throws Exception{
        String contentAddress = model.getHtmlModel().getContentAddress();
        int position=0;
        char[] chars = link.getHref().toCharArray();
        StringBuffer cssContentJ=new StringBuffer();
        String cssContentA=null;
        for(int i=0;i<link.getHref().length();i++){
            if(chars[i]==46){
                position++;
            }else {
                cssContentA = link.getHref().substring(i, link.getHref().length());
                break;
            }
        }
        String[] split = contentAddress.split("/");
        //代表当前
        if((position==0 || position==1) && split!=null){
            for(int y=0;y<=split.length-2;y++){
                cssContentJ.append(split[y]);
                cssContentJ.append("/");
            }
        } else {
            ExceptionTools.ThrowException("link点点有问题");
        }
        cssContentJ.append(cssContentA);
        //System.out.println("计算完毕后css的地址是:"+cssContentJ);
        StringBuffer fileValue = FileTools.getContent(cssContentJ.toString(),true);
        CssModel<List<CssBag>> cssModel=new CssModel<List<CssBag>>();
        cssModel.defineAction();
        List<CssBag> css=new ArrayList<CssBag>();
        cssModel.execute(fileValue.toString(),css);
        if(css!=null){
            Map<String,CssBag> map=new HashMap<String, CssBag>();
            for(CssBag cssBag:css){
                map.put(cssBag.getName().trim(),cssBag);
            }
            model.getPage().getCssMaps().putAll(map);
        }
    }

}
