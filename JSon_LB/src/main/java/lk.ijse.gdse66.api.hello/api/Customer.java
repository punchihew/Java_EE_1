package lk.ijse.gdse66.api.hello.api;


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.api.hello.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/test")
public class Customer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Student student = new Student("C001", "kamal", 10);
        Student student2 = new Student("C002", "amal", 10);

        ArrayList<Student> students = new ArrayList<>();

        students.add(student);
        students.add(student2);

        Jsonb jsonb = JsonbBuilder.create();

//        Jsonb jsonb = JsonbBuilder.create();


//        String json = jsonb.toJson(student);
//        resp.getWriter().write(json);

//        jsonb.toJson(students,resp.getWriter());

    }
}
