package lk.ijse.gdse66.hello.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//using annotation web app
@WebServlet(name = "Hello_Servlet" , value = "/" ,loadOnStartup = 1 , initParams = {
         @WebInitParam(name = "usesrname" , value = "root"),
         @WebInitParam(name = "password" , value = "1234"),
         @WebInitParam(name = "url" , value = "jdbc:mysql://localhost:3306/gdse66_hello")
})

public class Hello_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Doget");

    }
}
