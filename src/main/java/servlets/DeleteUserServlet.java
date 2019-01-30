package servlets;

import entity.User;
import service.UserService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteUserServlet extends HttpServlet{
    private UserService userService;

    public DeleteUserServlet(UserService userService) {
        this.userService = userService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        Integer id =Integer.parseInt (request.getParameter("id"));

        userService.delete(id);
        response.sendRedirect("/users");
    }
}
