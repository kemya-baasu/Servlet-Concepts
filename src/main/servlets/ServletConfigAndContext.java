package main.servlets;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(
        urlPatterns = { "/login" },
        initParams = {
                @WebInitParam(name = "databaseUrl", value = "jdbc:postgresql://localhost/EmployeeData"),
                @WebInitParam(name = "username", value = "postgres"),
                @WebInitParam(name = "password", value = "postgres")
        }
)
public class ServletConfigAndContext extends HttpServlet {
    Connection c=null;
    ArrayList list=new ArrayList();
    @Override
    public void init(ServletConfig config) {
        try {
            super.init(config);
            System.out.println(config.getInitParameter("databaseUrl")+"--"+config.getInitParameter("username") +"--"+ config.getInitParameter("password"));
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(config.getInitParameter("databaseUrl"),config.getInitParameter("username") , config.getInitParameter("password"));
            Statement s=c.createStatement();
           ResultSet rs= s.executeQuery("select * from employeedetails");
            while(rs.next()){
                list.add(rs.getString("employee_name"));
                System.out.println(rs.getString("employee_name"));
            }
            s.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("ID","17369");
        PrintWriter out=resp.getWriter();
        out.println(list);
        out.println(req.getParameter("name"));
        ServletConfig config=getServletConfig();
        System.out.println(config.getInitParameter("username"));
        System.out.println(config.getInitParameter("password"));
        ServletContext context=getServletContext();
        System.out.println("Hi");
        String s=context.getInitParameter("name");
        System.out.println(s+"Hi");
        out.println("password-->"+context.getAttribute("name"));
        out.println(s);
        Cookie cookie=new Cookie("username",config.getInitParameter("username"));
        resp.addCookie(cookie);
//        resp.sendRedirect("/servletCommunication?username="+config.getInitParameter("username"));
        resp.sendRedirect("pageSwitch.html");
        try {
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
