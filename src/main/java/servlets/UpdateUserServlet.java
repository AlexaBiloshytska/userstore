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

public class UpdateUserServlet extends HttpServlet {
    private UserService userService = ServiceLocator.get(UserService.class);

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            Long age = Long.parseLong(request.getParameter("age"));
            Long salary = Long.parseLong(request.getParameter("salary"));
            LocalDate birth = LocalDate.parse(request.getParameter("birth"));

            //create User
            User user = new User();
            user.setId(id);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(age);
            user.setSalary(salary);
            user.setBirth(birth);

            userService.update(user);

            response.sendRedirect("/users");
    }
}