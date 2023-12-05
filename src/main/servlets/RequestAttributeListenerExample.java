package main.servlets;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestAttributeListenerExample implements ServletRequestAttributeListener {
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("Name : "+servletRequestAttributeEvent.getName()+"----"+servletRequestAttributeEvent.getValue());
    }

    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("Name : "+servletRequestAttributeEvent.getName()+"----"+servletRequestAttributeEvent.getValue());

    }

    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        System.out.println("Name : "+servletRequestAttributeEvent.getName()+"----"+servletRequestAttributeEvent.getValue());

    }
}
