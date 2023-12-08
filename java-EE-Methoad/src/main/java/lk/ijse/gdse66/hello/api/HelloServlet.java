package lk.ijse.gdse66.hello.api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Work..!!");
        resp.getWriter().println("Welcome Get Method");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Work..!!");
        resp.getWriter().println("Welcome Delete Method");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Work..!!");
        resp.getWriter().println("Welcome Put Method");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Work..!!");
        resp.getWriter().println("Welcome Post Method");
    }
}
