package main.servlets;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.*;
@WebFilter("/login")
public class FilterLoginCredentials implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse res=(HttpServletResponse) servletResponse;
        PrintWriter out=res.getWriter();
        System.out.println("in filter");
        if(req.getParameter("name").equals("Kemya")&&req.getParameter("password").equals("kemya")&&req.getParameter("button").equals("login")){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{
            res.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,"Wrong Credentials");
        }
    }

    public void destroy() {
    }
}
