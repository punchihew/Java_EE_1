package lk.ijse.gdse66.hello.api;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Hello Servlet 1");

        ServletConfig src = getServletConfig();
        String navishka = src.getInitParameter("Navishka");
        System.out.println("Servlet..1");
        System.out.println(navishka);

        ServletContext txt = getServletContext();
        String City = txt.getInitParameter("city");
        System.out.println(City);



    }
}
