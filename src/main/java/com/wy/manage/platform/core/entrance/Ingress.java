package com.wy.manage.platform.core.entrance;

import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.Page;

import java.io.*;
import java.net.URL;

/**
 * Created by tianye
 */
public class Ingress {
    public void handle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try {
            URL resource = Ingress.class.getClassLoader().getResource("template/simple/window/window.html");
            File file=new File(resource.toURI());

            Reader fr = new FileReader(file);
            BufferedReader bufr = new BufferedReader(fr);
            StringBuffer stringBuffer=new StringBuffer();
            String line = null;
            while((line = bufr.readLine())!=null) {
                stringBuffer.append(line);
            }
            System.out.println("打印读取窗口日志:"+stringBuffer);

            System.out.println();
            HtmlModel<Page> htmlModel=new HtmlModel<Page>();
            htmlModel.defineAction();
            Page page=new Page();

            htmlModel.execute(stringBuffer.toString(),page);
            System.out.println(page.getWidgetNodeTree());
        }catch (Exception e){
            System.out.println(e);
        }


    }
}
