package main.servlets;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/attribute")
public class SessionAndAttributes extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session1=  req.getSession();
        session1.setMaxInactiveInterval(30);
        session1.setAttribute("sessionId",session1.getId());
        System.out.println(session1.getAttribute("sessionId"));
//        session1.invalidate();
        ServletContext context=getServletContext();
        context.setAttribute("Key","value");
        PrintWriter out=resp.getWriter();
        out.println(context.getAttribute("Key"));
        System.out.println(context.getAttribute("Key"));
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("showContext");
        requestDispatcher.include(req,resp);

    }
}
