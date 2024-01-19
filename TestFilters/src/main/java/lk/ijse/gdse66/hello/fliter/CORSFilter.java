package lk.ijse.gdse66.hello.fliter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CORSFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("DoFilter");
        System.out.println("CORSFilter : dispatch to the next filter");

        chain.doFilter(req,res);

        System.out.println("CORSFilter : outgoing response");
    }
}
