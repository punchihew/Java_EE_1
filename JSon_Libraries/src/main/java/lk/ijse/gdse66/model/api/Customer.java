package lk.ijse.gdse66.model.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.model.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;




@WebServlet(name = "JsonBindServlet" , urlPatterns = "/test")
public class Customer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student student = new Student("C001", "kamal", 10);
        Jsonb jsonb = JsonbBuilder.create();
        String json = jsonb.toJson(student);
        resp.getWriter().write(json);
    }
}
