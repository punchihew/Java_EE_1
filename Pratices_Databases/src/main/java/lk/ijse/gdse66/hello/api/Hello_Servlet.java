package lk.ijse.gdse66.hello.api;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "Hello_Servlet" , value = "/MyServlet" , loadOnStartup = 1, initParams = {
        @WebInitParam(name ="username" , value = "root"),
        @WebInitParam(name = "password" , value = "1234"),
        @WebInitParam(name = "url" , value = "jdbc:mysql://localhost:3306/gdse66_hello")

})

public class  Hello_Servlet extends HttpServlet {
    private String username;
    private String password;
    private String url;


    //connecting eka hadagena dunne attrubete wala vallue eka illgannwa
    @Override
    public void init() throws ServletException {

        ServletConfig sc = getServletConfig();

        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");
        String address = sc.getInitParameter("url");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String id = req.getParameter("id");
//        String name = req.getParameter("name");
//        String address = req.getParameter("address");
//
//        System.out.printf("id=%s, name=%s, address=%s\n", id, name, address);
        Connection connection = null;
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        System.out.printf("id=%s, name=%s, address=%s\n", id, name, address);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection =   DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello","root","1234");
            PreparedStatement stn = connection.prepareStatement("INSERT INTO customer(id,name,address) VALUES (?,?,?)");

            stn.setString(1,id);
            stn.setString(2,name);
            stn.setString(3,address);

            stn.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

