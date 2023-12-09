package lk.ijse.gdse66.hello.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//using annotation web app
@WebServlet(name = "Hello_Servlet" , value = "MyServlet" ,loadOnStartup = 1 )

public class Hello_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    

    }
}
