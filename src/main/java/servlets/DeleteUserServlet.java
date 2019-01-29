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
    private String requestedPage = "delete_user.html";

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        // Parse request
        String action = request.getParameter("actiontype");
        if (action.equals("create")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
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

            userService.getAll();

        } else if (action.equals("delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            userService.delete(id);
        }

        List<User> users = userService.getAll();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("users", users);

        PageGenerator instance = PageGenerator.getInstance();
        String page = instance.getPage(requestedPage, pageVariables);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(page);
    }
}
