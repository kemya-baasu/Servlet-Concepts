package main.servlets;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/servletCommunication"})
public class SendRedirectExample extends HttpServlet {
    HashMap<String, String> keys = new HashMap<String, String>() {
        {
            put("employeeId", "emp_id");
            put("employeeName", "emp_name");
            put("employeeEmail", "emp_email");
            put("employeeSalary", "emp_salary");
            put("employeeDesignation", "emp_designation");
            put("employeeAddress", "emp_gender");
        }
    };
    Connection c= null;
    PreparedStatement s= null;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out=resp.getWriter();
        ServletContext context=getServletContext();
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(context.getInitParameter("databaseUrl"),context.getInitParameter("username") , context.getInitParameter("password"));
            System.out.println(context.getInitParameter("databaseUrl")+"--"+context.getInitParameter("username")+"---"+context.getInitParameter("password"));
            s=c.prepareStatement("select * from employeedetails where emp_id=?");
             s.setInt(1,Integer.parseInt(req.getParameter("employeeId")));
            ResultSet resultSet = s.executeQuery();
            resp.addHeader("Name","Kemya");

            //Cookies
            Cookie cookie=new Cookie("employeeId",req.getParameter("employeeId"));
            cookie.setMaxAge(0);//changing the maximum age to 0 seconds
            resp.addCookie(cookie);

//            while (resultSet.next()) {
//                for (Map.Entry<String, String> entry : keys.entrySet()) {
//                    out.println((String.format("| %-25s|   ", entry.getKey())) + (String.format("%-40s|",  resultSet.getString(entry.getValue()))));
//                }
//            }
            if(req.getParameter("employeeId").equals("17369")) {
                req.setAttribute("emp_id", req.getParameter("employeeId"));
                RequestDispatcher rd = req.getRequestDispatcher("/projectDetails");
                rd.forward(req, resp);
            }else{
                resp.reset();
                out.print("Sorry This id can't be taken");
//                resp.flushBuffer();
                RequestDispatcher rd = req.getRequestDispatcher("/pageSwitch.html");
                rd.include(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
