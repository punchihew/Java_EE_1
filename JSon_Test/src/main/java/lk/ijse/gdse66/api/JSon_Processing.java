package lk.ijse.gdse66.api;

import jakarta.json.*;
import jdk.nashorn.internal.runtime.JSONListAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

//@WebServlet(name = "Hello_servlet" , value = "/" , loadOnStartup = 1)

@WebServlet(urlPatterns = "/test")
    public class JSon_Processing extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        // using Json-p libery
//        JsonReader reader = Json.createReader(req.getReader());
//        JsonObject jsonObject = reader.readObject();
////        System.out.println(jsonObject);
//
////
////        String id = jsonObject.getString("id");
////        String name = jsonObject.getString("name");
////        JsonObject address = jsonObject.getJsonObject("address");
////
////        int no = address.getInt("no");
////        String street = address.getString("street");
////        String city = address.getString("city");
////
////        JsonArray contacts = jsonObject.getJsonArray("contacts");
////        String fisrtcontact = contacts.getString(0);
////        String secondcontact = contacts.getString(1);
////
////        System.out.println(id);
////        System.out.println(name);
////        System.out.println(address);
////        System.out.println("no"+no+ "street"+ street + "city" + city);
////        System.out.println("fisrt Conatct " + fisrtcontact);
////        System.out.println("second Conatct " + secondcontact);
//
//        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
//        objectBuilder.add("id" , "C001");
//        objectBuilder.add("name" ,"kamal");
//
//        JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
//        addressBuilder.add("no",81);
//        addressBuilder.add("Street" , "Sinhasana Road");
//        addressBuilder.add("city","matara");
//
//        objectBuilder.add("address",addressBuilder);
//
//        JsonArrayBuilder contactarrayBuilder = Json.createArrayBuilder();
//        contactarrayBuilder.add("011-2221208");
//        contactarrayBuilder.add("011-2221208");
//
//        objectBuilder.add("contacts" , contactarrayBuilder);
//
//        resp.getWriter().write(objectBuilder.build().toString());
//
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *from customer");
            ResultSet set = preparedStatement.executeQuery();

            while (set.next()){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id",set.getString(1));
                objectBuilder.add("name",set.getString(2));
                objectBuilder.add("address",set.getString(3));

                arrayBuilder.add(objectBuilder.build());
            }
            resp.setContentType("application/json");
            resp.getWriter().write(arrayBuilder.build().toString());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        //apita ona widihata get karagnna methoad customize karagnna
//        String action = req.getParameter("action");
//        if (action==null){
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"action is emplty");
//            System.out.println("action is not avaible");
//        }
//        if (action.equalsIgnoreCase("GETALL")){
//            GettallCustomer();
//        }else if (action.equalsIgnoreCase("GETONE")){
//            getCustomerbyId();
//        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        if (id==null || !id.matches("C\\d{3}")){
//            resp.sendError();
            resp.getWriter().write("id is empty or id is invalid");
        }else if (name == null || name.matches("[A-Za-z]+")){
            resp.getWriter().write("id is empty or id is invalid");
        }else if (address== null ){
            resp.getWriter().write("id is empty or id is invalid");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello", "root", "1234");
            PreparedStatement stn = connection.prepareStatement("INSERT INTO customer(id,name,address) VALUES(?,?,?)");

            stn.setString(1,id);
            stn.setString(2,name);
            stn.setString(3,address);


            if(stn.executeUpdate() != 0){
                resp.getWriter().write("Customer Save Successfull");
            }{
                resp.getWriter().write("Fail To Save Customer");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

//        String name = req.getParameter("id");
//        String id = req.getParameter("name");
//        String address = req.getParameter("address");

//        System.out.println(id);
//        System.out.println(name);
//        System.out.println(address);

        //Deta Get using JSon Deta Reqest
//        JsonReader reader = Json.createReader(req.getReader());
//        JsonObject jsonObject = reader.readObject();
//        String id = jsonObject.getString("id");
//        String name = jsonObject.getString("name");
//        String address = jsonObject.getString("address");
//        System.out.println(id);
//        System.out.println(name);
//        System.out.println(address);

//        Deta Responses

//        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
//        objectBuilder.add("id","C001");
//        objectBuilder.add("name","Lakshan");
//        objectBuilder.add("address","Matara");
//        JsonObject jsonObject = objectBuilder.build();
//        resp.getWriter().write(jsonObject.toString());


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        String id = req.getParameter("id");

        System.out.printf("id=%s\n", id);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello", "root", "1234");
            PreparedStatement stn = connection.prepareStatement("DELETE FROM customer WHERE id=?");

            stn.setString(1, id);
            if(stn.executeUpdate() != 0){
                resp.setStatus(204,"Customer Deleted..!!");
                resp.getWriter().write("Customer Save Successfull");
            }{
                resp.getWriter().write("Fail To Save Customer");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null;
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection =   DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello","root","1234");
            PreparedStatement stn = connection.prepareStatement(" UPDATE customer SET name=?,address=? WHERE id=?");


            stn.setString(1,name);
            stn.setString(2,address);
            stn.setString(3,id);



            if(stn.executeUpdate()>0){
                resp.setStatus(200,"Customer Saved");
                resp.getWriter().write("Customer Save Successfull");
            }{
                resp.getWriter().write("Fail To Save Customer");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
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
