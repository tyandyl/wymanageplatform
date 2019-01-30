import com.wy.manage.platform.core.entrance.Ingress;
import com.wy.manage.platform.core.entrance.TablePanelIngress;

import java.io.IOException;

/**
 * Created by tianye
 */
@javax.servlet.annotation.WebServlet(name = "TablePanel")
public class TablePanel extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 7249899664918618385L;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        Ingress ingress=new TablePanelIngress();
        ingress.handle(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        Ingress ingress=new TablePanelIngress();
        ingress.handle(request,response);
    }
}



