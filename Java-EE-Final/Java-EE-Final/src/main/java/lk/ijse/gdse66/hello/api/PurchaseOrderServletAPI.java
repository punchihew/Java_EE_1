package lk.ijse.gdse66.hello.api;

import jakarta.json.*;

import lk.ijse.gdse66.hello.bo.BoFactory;
import lk.ijse.gdse66.hello.bo.custom.PurchaseOrderBO;
import lk.ijse.gdse66.hello.dto.OrderDTO;
import lk.ijse.gdse66.hello.dto.OrderDetailsDTO;
import lk.ijse.gdse66.hello.util.ResponseUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/placeOrder")
public class PurchaseOrderServletAPI extends HttpServlet {

    PurchaseOrderBO purchaseOrderBO= (PurchaseOrderBO) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Content-Type","application/json");

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        ServletContext servletContext =getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");
        PrintWriter writer = resp.getWriter();
        try (Connection connection=pool.getConnection()){

            JsonArrayBuilder allOrders = Json.createArrayBuilder();
            ArrayList<OrderDTO> all = purchaseOrderBO.getAllOrders(connection);

            for (OrderDTO orderDTO:all){
                JsonObjectBuilder order = Json.createObjectBuilder();
                order.add("oID",orderDTO.getOrderID());
                order.add("oDate", String.valueOf(orderDTO.getDate()));
                order.add("oCusID",orderDTO.getCustomerID());

                allOrders.add(order).build();


            }
            writer.println(allOrders.build());

        } catch (ClassNotFoundException e) {
            resp.getWriter().println(e.getMessage());
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");


        ArrayList<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();


        ServletContext servletContext =getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String oID = jsonObject.getString("orderId");
        String oDate = jsonObject.getString("date");
        String oCusID = jsonObject.getString("cusId");
        JsonArray oCartItems = jsonObject.getJsonArray("itemDet");


        System.out.println(oCusID);
        System.out.println(oDate);
        System.out.println(oID);

        try {
            Connection connection = pool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders VALUES (?,?,?)");

            preparedStatement.setString(1,oID);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(3,oCusID);

            int i = preparedStatement.executeUpdate();
            connection.commit();

            if (i>0){

                Connection connection1 = pool.getConnection();
                connection1.setAutoCommit(false);

                PreparedStatement preparedStatement1 = connection1.prepareStatement("INSERT INTO orderdetail VALUES (?,?,?,?)");

                for (JsonValue obj : oCartItems){

                    JsonObject jsonObject1 = obj.asJsonObject();
                    String code = jsonObject1.getString("code");
                    Integer avQty = Integer.valueOf(jsonObject1.getString("avQty"));
                    Double price = Double.valueOf(jsonObject1.getString("price"));

                    preparedStatement1.setString(1,oID);
                    preparedStatement1.setString(2,code );
                    preparedStatement1.setInt(3,avQty);
                    preparedStatement1.setDouble(4,price);

                    preparedStatement1.executeUpdate();


                }
                connection1.commit();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }



//
//        try (Connection connection= pool.getConnection()){
//
//
//            for (int i = 0; i < oCartItems.size(); i++) {
//                String iId= oCartItems.getJsonArray(i).getString(0);
//                int iQty= Integer.parseInt(oCartItems.getJsonArray(i).getString(3));
//                double iPrice= Double.parseDouble(oCartItems.getJsonArray(i).getString(2));
//
//                System.out.println(iId);
//                System.out.println(iQty);
//                System.out.println(iPrice);
//                orderDetailsDTOS.add(new OrderDetailsDTO(iId,oID,iQty,iPrice));
//            }
//
//            OrderDTO orderDTO = new OrderDTO(oID,oDate,oCusID,orderDetailsDTOS);
//            if (purchaseOrderBO.purchaseOrder(connection,orderDTO)){
//                resp.getWriter().print(ResponseUtil.getJson("OK","Successfully Added !"));
//            }
//
//
//        } catch (ClassNotFoundException e) {
//            resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));
//
//        } catch (SQLException e) {
//            resp.getWriter().print(ResponseUtil.getJson("Error",e.getMessage()));
//
//        }

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE,PUT,GET");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }


}
