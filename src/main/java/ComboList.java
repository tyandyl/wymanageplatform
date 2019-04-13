import com.wy.manage.platform.core.entrance.ComboListIngress;
import com.wy.manage.platform.core.entrance.Ingress;
import com.wy.manage.platform.core.entrance.TablePanelIngress;
import com.wy.manage.platform.core.model.HtmlModel;
import com.wy.manage.platform.core.widget.Page;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by tianye
 */
@javax.servlet.annotation.WebServlet(name = "ComboList")
public class ComboList extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 7249899664918618385L;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        Ingress ingress=new ComboListIngress();
        ingress.handle(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        Ingress ingress=new ComboListIngress();
        ingress.handle(request,response);
    }
}
