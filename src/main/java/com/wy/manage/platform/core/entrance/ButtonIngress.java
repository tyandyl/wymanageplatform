package com.wy.manage.platform.core.entrance;

import com.alibaba.fastjson.JSONObject;
import com.wy.manage.platform.core.bean.Result;
import com.wy.manage.platform.core.model.BasicModel;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.*;
import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tianye
 */
public class ButtonIngress extends Ingress{


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            WidgetModel model=new WidgetModel(){
                @Override
                public BasicModel initLoadHtmlModel() {
                    return  new HtmlModel<Page>(){
                        @Override
                        public String getRegularAddress() {
                            return "regular/widget.properties";
                        }
                        @Override
                        public String getContentAddress() {
                            return "template/button/button.html";
                        }
                    };
                }

                @Override
                public Page loadPage(WidgetModelParamResult paramResult) {
                    HttpSession session = paramResult.getRequest().getSession(true);
                    return Context.get(session.getId());
                }
                @Override
                public void handleParamMap(WidgetModelParamResult paramResult){
                    super.handleParamMap(paramResult);
                    String[] addressUrl = paramResult.getJParam().get("addressUrl");
                    if(addressUrl!=null){
                        paramResult.getParam().put("addressUrl",addressUrl[0]);
                    }
                    String[] targetList = paramResult.getJParam().get("targetList");
                    if(targetList!=null){
                        paramResult.getParam().put("targetList",targetList[0]);
                    }
                    String[] selectValueList = paramResult.getJParam().get("selectValueList");
                    if(selectValueList!=null){
                        paramResult.getParam().put("selectValueList",selectValueList[0]);
                    }
                }
                @Override
                public void  handleEdit(WidgetModelParamResult result,Page page)throws Exception{
                    String id = result.getParam().get("id");
                    WidgetNode widgetNode = page.getWidgetNodeTree().getNodeMap().get(id);
                    //根据id获取当前点击的按钮，然后获取当前按钮的target，注：每个窗口打开的时候，会把选中的控件当做
                    Set<String> targetParam = widgetNode.getData().getRegisterParam().getTargetParam();
                    if(targetParam==null){
                        targetParam=new HashSet<String>();
                    }
                    String targets=result.getParam().get("targetList");
                    if(targets!=null){
                        String[] splits=targets.split(",");
                        targetParam.addAll( Arrays.asList(splits));
                    }
                    List<List<CurWidget>> lists=new ArrayList<List<CurWidget>>();
                    if(targetParam!=null && targetParam.size()>0){
                        for(String targetId:targetParam){
                            WidgetNode targetIdNode = page.getWidgetNodeTree().getNodeMap().get(targetId);
                            String addressUrl = result.getParam().get("addressUrl");
                            targetIdNode.getData().setUrl(addressUrl);

                            String selectValueList = result.getParam().get("selectValueList");
                            if(selectValueList!=null && !selectValueList.trim().equalsIgnoreCase("")){
                                String[] split1 = selectValueList.split(",");
                                if(split1!=null && split1.length>0){
                                    for(String u:split1){
                                        WidgetNode widgetNode1 = page.getWidgetNodeTree().getNodeMap().get(u);
                                        String name = widgetNode1.getData().getTagType().getName();
                                        targetIdNode.getData().getRegisterParam().getRequestParam().add(u+":"+name+":"+widgetNode1.getData().getDataFlag());
                                    }
                                }
                            }

                            List<CurWidget> curWidgets=new ArrayList<CurWidget>();
                            page.fillNodeEX(targetIdNode,curWidgets);
                            lists.add(curWidgets);
                            result.getResult().setResult(lists);


                        }
                    }


                }
            }.init(request).execute();
            Result result = model.getParamResult().getResult();
            String strPage = JSONObject.toJSONString(result);
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-type", "text/html;charset=UTF-8");//注意是分号，不能是逗号
            OutputStream out = response.getOutputStream();
            out.write(strPage.getBytes("UTF-8"));
        }catch (Exception e){
            System.out.println("创建按钮报错");
        }
    }
}
