package servlets;

import org.junit.Test;

public class UsersServletTest {
    UsersServlet usersServlet = new UsersServlet();
    @Test
    public void sqlStatement() throws Exception {
        usersServlet.userDao.getUsers();
        System.out.println();
    }

}