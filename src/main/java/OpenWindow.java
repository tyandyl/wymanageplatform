import com.wy.manage.platform.core.entrance.Ingress;
import com.wy.manage.platform.core.entrance.WindowIngress;
import com.wy.manage.platform.core.entrance.WindowOpenIngress;

import java.io.IOException;

/**
 * Created by tianye
 */
@javax.servlet.annotation.WebServlet(name = "OpenWindow")
public class OpenWindow extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 7249899664918618385L;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        Ingress ingress=new WindowOpenIngress();
        ingress.handle(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        Ingress ingress=new WindowOpenIngress();
        ingress.handle(request,response);
    }
}
