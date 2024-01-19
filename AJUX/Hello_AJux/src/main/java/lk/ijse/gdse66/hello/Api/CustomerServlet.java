package lk.ijse.gdse66.hello.Api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.hello.DTO.CustomerDTO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


@WebServlet(name = "Customer", urlPatterns = "/customers", loadOnStartup = 1, initParams = {
        @WebInitParam(name = "username" , value = "root"),
        @WebInitParam(name = "password" , value = "MYsql@123@"),
        @WebInitParam(name = "url" , value = "jdbc:mysql://localhost:3306/gdse66_hello")
})

public class CustomerServlet extends HttpServlet {
    private String username;
    private String password;
    private String url;

    @Override
    public void init() throws ServletException {
        /*ServletConfig is used to get configuration information such as database url, mysql username and password*/
        ServletConfig sc = getServletConfig();
        username = sc.getInitParameter("username");
        password = sc.getInitParameter("password");
        url = sc.getInitParameter("url");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        /*resp.addHeader("Access-Control-Allow-Origin", "*");*/



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String action = req.getParameter("action");
        if(action ==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "action is empty");
        } else-if(action.equalsIgnoreCase("GETALL")){
            getAllCustomers();
        } else if (action.equalsIgnoreCase("GETONE")) {
            getCustomerById();
        }*/

        resp.addHeader("Access-Control-Allow-Origin", "*");

        Connection connection = null;

        /*create a database connection and fetch data in database*/
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM customer");
            ResultSet rst = stm.executeQuery();

            ArrayList<CustomerDTO> customerList = new ArrayList<>();

            while (rst.next()) {
                String id = rst.getString("id");
                String name = rst.getString("name");
                String address = rst.getString("address");
                System.out.printf("id=%s, name=%s, address=%s\n", id, name, address);

                customerList.add(new CustomerDTO(id, name, address));
            }

            resp.setContentType("application/json"); //set the MIME type of the content of the response (Thus, add response header called "Content-Type")

            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(customerList, resp.getWriter());


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        String id = req.getParameter("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE id=?");
            stm.setString(1, id);

            if (stm.executeUpdate() != 0) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete the customer!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
