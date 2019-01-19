import com.wy.manage.platform.core.entrance.Ingress;

import java.io.IOException;

/**
 * Created by tianye13 on 2019/1/18.
 */
@javax.servlet.annotation.WebServlet(name = "Dog")
public class Dog extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 7249899664918618385L;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("uuuuuuuuuuuuuuuuuuuuu");
        Ingress ingress=new Ingress("Dog");
        ingress.handle(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
        Ingress ingress=new Ingress("Dog");
        ingress.handle(request,response);
    }
}


