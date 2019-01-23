package servlets;
import dao.JdbcUserDao;
import entity.User;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {

    public JdbcUserDao userDao = new JdbcUserDao();

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDao.getUsers();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("users", users);
        response.getWriter().println(PageGenerator.instance().getPage("templates/lab1/page.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        // Parse request
        String action = request.getParameter("actiontype");
        if (action.equals("create") ){
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            Long age =  Long.parseLong(request.getParameter("age"));
            Long salary = Long.parseLong(request.getParameter("salary"));
            LocalDate birth = LocalDate.parse(request.getParameter("birth"));
            //create User
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(age);
            user.setSalary(salary);
            user.setBirth(birth);

            userDao.addNewUser(user);

        } else if (action.equals("delete")){
            Integer id = Integer.parseInt(request.getParameter("id"));

            try {
                userDao.deleteUser(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        List<User> users = userDao.getUsers();
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("users", users);

        response.getWriter().println(PageGenerator.instance().getPage("templates/lab1/page.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

}
