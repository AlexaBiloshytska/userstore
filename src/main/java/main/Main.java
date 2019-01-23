package main;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import servlets.UsersServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        UsersServlet allRequestsServlet = new UsersServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/users");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
