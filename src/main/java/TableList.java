import com.wy.manage.platform.core.entrance.Ingress;
import com.wy.manage.platform.core.entrance.TableListIngress;
import com.wy.manage.platform.core.entrance.TablePanelIngress;

import java.io.IOException;

/**
 * Created by tianye13 on 2019/3/18.
 */
@javax.servlet.annotation.WebServlet(name = "TableList")
public class TableList extends javax.servlet.http.HttpServlet{
    private static final long serialVersionUID = 1710523888426349979L;
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        Ingress ingress=new TableListIngress();
        ingress.handle(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        Ingress ingress=new TableListIngress();
        ingress.handle(request,response);
    }
}
