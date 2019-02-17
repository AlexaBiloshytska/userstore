package servlets;
import entity.User;
import service.UserService;
import templater.PageGenerator;
import templater.ServiceLocator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {

    private UserService userService = ServiceLocator.get(UserService.class);
    private String requestedPage ="index.html";

    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[info] getting all users");

        List<User> users = userService.getAll();

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("users", users);

        PageGenerator instance= PageGenerator.getInstance();
        String page = instance.getPage(requestedPage, pageVariables);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(page);
    }
}
