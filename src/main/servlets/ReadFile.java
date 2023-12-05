package main.servlets;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@WebServlet("/readFile")
public class ReadFile extends HttpServlet implements  Runnable {
    HttpServletRequest req;
    HttpServletResponse resp;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req=req;
        this.resp=resp;
        ReadFile r=new ReadFile();
        Thread t=new Thread(r);
        t.start();
    }
    protected void readLines(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context=getServletContext();
        FileReader fr=new FileReader(context.getInitParameter("filePath"));
        BufferedReader bufferedReader=new BufferedReader(fr);
        String line="";
        int count=0;
        context.setAttribute("NumberOfLinesRead",count);
        while((line=bufferedReader.readLine())!=null){
            context.setAttribute("NumberOfLinesRead", count);
            count++;
            System.out.println(count);
            resp.flushBuffer();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        bufferedReader.close();
        fr.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        ServletContext context=getServletContext();
        out.println("Number of Lines Read : "+context.getAttribute("NumberOfLinesRead"));
    }

    public void run() {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        ReadFile r=new ReadFile();
    }
}
