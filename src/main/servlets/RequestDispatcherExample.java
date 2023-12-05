package main.servlets;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

@WebServlet(urlPatterns = "/projectDetails")
public class RequestDispatcherExample extends HttpServlet {
    ArrayList<String> keys = new ArrayList<String>(Arrays.asList("emp_id","emp_name","project_id","project_name"));
    Connection c= null;
    PreparedStatement s= null;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out=resp.getWriter();
        ServletContext context=getServletContext();
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(context.getInitParameter("databaseUrl"), context.getInitParameter("username"), context.getInitParameter("password"));
            System.out.println(context.getInitParameter("databaseUrl") + "--" + context.getInitParameter("username") + "---" + context.getInitParameter("password"));
            s = c.prepareStatement("select e.emp_id ,e.emp_name,e2.project_id,p.project_name  from employeedetails e inner join empprojrel e2 on e.emp_id =e2.emp_id inner join projects p on e2.project_id =p.project_id where e.emp_id=?");
            System.out.println(req.getAttribute("emp_id"));
            out.println(req.getContextPath());
            s.setInt(1, Integer.parseInt( req.getParameter("employeeId")));
            System.out.println(s);
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                for (int i=0;i<keys.size();i++) {
                    out.println((String.format("| %-25s|   ", keys.get(i))) + (String.format("%-40s|", resultSet.getString(keys.get(i)))));
                }
                out.println("---------------------------------------------------------------------------------");
            }

            // fetch Cookies
            out.println("Cookies ---------------------------->");
            Cookie[] cookies=req.getCookies();
            for (int i = 0; i <cookies.length ; i++) {
                out.println("Name: "+cookies[i].getName()+"Value: "+cookies[i].getValue());
            }
        }catch (Exception e){
            System.out.println("Exception : "+e);
        }
    }
}
