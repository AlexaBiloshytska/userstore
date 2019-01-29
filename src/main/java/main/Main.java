package main;

import dao.DataSource;
import dao.JdbcUserDao;
import dao.UserDao;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import service.DefaultUserService;
import service.UserService;
import servlets.UsersServlet;
import templater.ServiceLocator;

import java.net.ServerSocket;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception {
        //config dao
        DataSource dataSource = DataSource.getInstance();
        Connection connection = dataSource.getConnection();

        UserDao userDao = new JdbcUserDao(connection);
        ServiceLocator.register(UserDao.class, userDao);

        // config services
        UserService userService = new DefaultUserService(userDao);

        //config servlets
        UsersServlet usersServlet = new UsersServlet(userService);

        // servlets register
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(usersServlet), "/users");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
