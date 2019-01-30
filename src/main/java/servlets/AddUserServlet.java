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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddUserServlet extends HttpServlet {
    private UserService userService;
    private String requestedPage ="index.html";


    public AddUserServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        Long age = Long.parseLong(request.getParameter("age"));
        Long salary = Long.parseLong(request.getParameter("salary"));
        LocalDate birth = LocalDate.parse(request.getParameter("birth"));

        //create User
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setSalary(salary);
        user.setBirth(birth);

        userService.add(user);

        userService.getAll();
        response.sendRedirect("/users");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
