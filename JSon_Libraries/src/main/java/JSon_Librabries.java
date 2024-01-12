import jakarta.json.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/test")
public class JSon_Librabries extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // using Json-p libery
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
//        System.out.println(jsonObject);

//
//        String id = jsonObject.getString("id");
//        String name = jsonObject.getString("name");
//        JsonObject address = jsonObject.getJsonObject("address");
//
//        int no = address.getInt("no");
//        String street = address.getString("street");
//        String city = address.getString("city");
//
//        JsonArray contacts = jsonObject.getJsonArray("contacts");
//        String fisrtcontact = contacts.getString(0);
//        String secondcontact = contacts.getString(1);
//
//        System.out.println(id);
//        System.out.println(name);
//        System.out.println(address);
//        System.out.println("no"+no+ "street"+ street + "city" + city);
//        System.out.println("fisrt Conatct " + fisrtcontact);
//        System.out.println("second Conatct " + secondcontact);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id" , "C001");
        objectBuilder.add("name" ,"kamal");

        JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
        addressBuilder.add("no",81);
        addressBuilder.add("Street" , "Sinhasana Road");
        addressBuilder.add("city","matara");

        objectBuilder.add("address",addressBuilder);

        JsonArrayBuilder contactarrayBuilder = Json.createArrayBuilder();
        contactarrayBuilder.add("011-2221208");
        contactarrayBuilder.add("011-2221208");

        objectBuilder.add("contacts" , contactarrayBuilder);

        resp.getWriter().write(objectBuilder.build().toString());


    }
}
