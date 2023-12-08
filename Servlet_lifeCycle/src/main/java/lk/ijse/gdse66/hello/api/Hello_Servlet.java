package lk.ijse.gdse66.hello.api;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Hello_Servlet  extends HttpServlet {

    static{
        System.out.println("Servlet is being initialized");
    }
    public Hello_Servlet(){
        System.out.println("Servlet(): Constructomer ");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("initServletConfig");
        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("int()");
//        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Hello Servlet 1");
        System.out.println("Hello");

    }
}
