package dao;

import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class JdbcUserDao {
    // JDBC driver name and database URL
    public static final String DB_URL = "jdbc:h2:mem:test;INIT=runscript from 'classpath:init.sql'";

    //  Database credentials
    private static final String USER = "sa";
    private static final String PASS = "";
    private static final String sql = "SELECT id, first_name, last_name, age, dateOfBirth,salary FROM USERS ";
    private  static final String queryAdd= "insert into users(first_name, last_name, age, dateOfBirth, salary) values(?,?,?,?,?)";
    private static final String delete = "delete from users where id =?";

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DataSource.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);){

            while (resultSet.next()) {
                User user = mapUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Unable to process resultSet");
            throw new RuntimeException(e);
        }
        return users;
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        // Retrieve by column name
        String first = resultSet.getString("first_name");
        String last = resultSet.getString("last_name");
        LocalDate birth = resultSet.getDate("dateOfBirth").toLocalDate();
        Long age = resultSet.getLong("age");
        Long salary = resultSet.getLong("salary");

        // fill list
        User user = new User();
        user.setAge(age);
        user.setBirth(birth);
        user.setFirstName(first);
        user.setLastName(last);
        user.setSalary(salary);
        return user;
    }

    public void addNewUser(User user){

        try (Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(queryAdd);) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setLong(3, user.getAge());
            preparedStatement.setDate(4, Date.valueOf(user.getBirth()));
            preparedStatement.setLong(5, user.getSalary());
            preparedStatement.execute();
            connection.commit();

            System.out.println("Data is successfully inserted: "+ user);

        } catch (Exception e) {
            System.out.println("Failed to insert new user"+ user);
            e.printStackTrace();

        }
    }
    public void deleteUser(int id) throws SQLException {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(delete);){

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            connection.commit();
            System.out.println("user deletion is successful");
        }

   }


}

