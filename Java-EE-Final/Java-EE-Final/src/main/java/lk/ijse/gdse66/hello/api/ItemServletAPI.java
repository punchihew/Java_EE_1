package lk.ijse.gdse66.hello.api;

import jakarta.json.*;
import lk.ijse.gdse66.hello.bo.BoFactory;
import lk.ijse.gdse66.hello.bo.custom.ItemBO;
import lk.ijse.gdse66.hello.dto.ItemDTO;
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
import java.util.ArrayList;

@WebServlet(urlPatterns = "/item")
public class ItemServletAPI extends HttpServlet {

    ItemBO itemBO = (ItemBO) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext =getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");
        try(Connection connection = pool.getConnection()) {

            PrintWriter writer = resp.getWriter();

            JsonArrayBuilder allItems = Json.createArrayBuilder();

            ArrayList<ItemDTO> all = itemBO.getAllItems(connection);

            for (ItemDTO itemDTO:all) {

                JsonObjectBuilder item = Json.createObjectBuilder();

                item.add("code",itemDTO.getItemCode());
                item.add("desc",itemDTO.getItemName());
                item.add("unitPrice",itemDTO.getItemPrice());
                item.add("qty",itemDTO.getItemQty());

                allItems.add(item.build());
            }

            writer.print(allItems.build());


        } catch (ClassNotFoundException e) {
            resp.getWriter().println(e.getMessage());
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("txtItemID");
        String description = req.getParameter("txtItemName");
        double unitPrice = Double.parseDouble(req.getParameter("txtItemPrice"));
        int qty = Integer.parseInt(req.getParameter("txtItemQty"));

        ServletContext servletContext =getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

            try (Connection connection = pool.getConnection()){
                PreparedStatement stn = connection.prepareStatement("INSERT INTO item(code,description,unitPrice,qty) VALUES(?,?,?,?)");

                stn.setString(1,code);
                stn.setString(2,description);
                stn.setDouble(3,unitPrice);
                stn.setInt(4,qty);

                stn.executeUpdate();
                resp.getWriter().write("print!!");


            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        String code = jsonObject.getString("code");
        String desc = jsonObject.getString("desc");
        double unitPrice = Double.parseDouble(jsonObject.getString("unitPrice"));
        int qty = Integer.parseInt(jsonObject.getString("qty"));

        ServletContext servletContext = getServletContext();
        BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()) {
            PreparedStatement stn = connection.prepareStatement("UPDATE customer SET desc=?,unitPrice=?,qty=? WHERE code=?");

            stn.setString(1,code);
            stn.setString(2,desc);
            stn.setDouble(3,unitPrice);
            stn.setInt(4,qty);

            stn.executeUpdate();
            resp.getWriter().write("print!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String itemID = req.getParameter("code");

            ServletContext servletContext = getServletContext();
            BasicDataSource pool = (BasicDataSource) servletContext.getAttribute("dbcp");

            try (Connection connection = pool.getConnection()) {

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}
