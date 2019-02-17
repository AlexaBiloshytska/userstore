package servlets;

import entity.User;
import service.UserService;
import templater.ServiceLocator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class AddUserServlet extends HttpServlet {
    private UserService userService = ServiceLocator.get(UserService.class);

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

        response.sendRedirect("/users");
    }
}
