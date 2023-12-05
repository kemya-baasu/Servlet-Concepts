package main.servlets;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListenerExample implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Context Initialization Listener : "+servletContextEvent.getServletContext().getInitParameterNames());
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context Destroyed Listener : "+servletContextEvent.getServletContext().getAttributeNames());
    }
}
