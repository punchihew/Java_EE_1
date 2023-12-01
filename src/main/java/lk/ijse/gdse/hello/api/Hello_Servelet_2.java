package lk.ijse.gdse.hello.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Hello_Servelet_2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Incoming request: Hello Servlet" +req.getRemoteAddr());
        resp.getWriter().println("Hello I From the Hello Server 2");
    }
}
