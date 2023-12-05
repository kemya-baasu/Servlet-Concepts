package main.servlets;

import org.apache.catalina.SessionEvent;
import org.apache.catalina.SessionListener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerExample implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session Listener Created: "+httpSessionEvent.getSession().getMaxInactiveInterval()+"----"+httpSessionEvent.getSession().getMaxInactiveInterval());

    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session Listener Destroyed: "+httpSessionEvent.getSession().getMaxInactiveInterval()+"----"+httpSessionEvent.getSession().getId());

    }
}
