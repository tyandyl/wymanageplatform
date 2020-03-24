import com.wy.manage.platform.core.entrance.Ingress;
import com.wy.manage.platform.core.entrance.WindowIngress;
import com.wy.manage.platform.core.monitor.CPUMonitor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianye
 */
@javax.servlet.annotation.WebServlet(name = "Window")
public class Window extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 7249899664918618385L;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        CPUMonitor CPUMonitor =new CPUMonitor();
        CPUMonitor.start(1, TimeUnit.SECONDS);
        Ingress ingress=new WindowIngress();
        ingress.handle(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        CPUMonitor CPUMonitor =new CPUMonitor();
        CPUMonitor.start(1, TimeUnit.SECONDS);
        Ingress ingress=new WindowIngress();
        ingress.handle(request,response);
    }
}
