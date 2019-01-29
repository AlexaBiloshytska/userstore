package servlets;

import dao.UserDao;
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


public class AddUserServlet extends HttpServlet {
    private UserService userService;
    private String requestedPage ="add.html";

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();

        PageGenerator instance= PageGenerator.getInstance();
        String page = instance.getPage(requestedPage, pageVariables);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(page);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
