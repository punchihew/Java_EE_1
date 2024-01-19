package api;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import org.apache.commons.dbcp2.BasicDataSource;

@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource dbcp = new BasicDataSource(); //create connecting pool
        dbcp.setUsername("root");
        dbcp.setPassword("1234");
        dbcp.setUrl("jdbc:mysql://localhost:3306/gdse66_hello");
        dbcp.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbcp.setInitialSize(2);
        dbcp.setMaxTotal(5);

        ServletContext sc = sce.getServletContext();
        sc.setAttribute("dbcp",dbcp);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);

    }
}