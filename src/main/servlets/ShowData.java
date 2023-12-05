package main.servlets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
@WebServlet("/showContext")
public class ShowData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session1=  req.getSession();
        Enumeration context=getServletContext().getInitParameterNames();
        InputStream in= req.getInputStream();
        PrintWriter out= resp.getWriter();
        out.println(session1.getAttribute("sessionId"));
        out.println(context.nextElement());
        out.println(req.getProtocol());
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.println(String.valueOf(bytesRead));
        }
    }


}
