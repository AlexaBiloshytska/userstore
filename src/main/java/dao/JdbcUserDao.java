package dao;

import dao.mapper.userMapper;
import entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    //  Database credentials
    private static final String USER = "sa";
    private static final String PASS = "";
    private static final String sql = "SELECT id, first_name, last_name, age, dateOfBirth,salary FROM USERS ";
    private  static final String queryAdd= "insert into users(first_name, last_name, age, dateOfBirth, salary) values(?,?,?,?,?)";
    private static final String delete = "delete from users where id =?";
    private final static String UPDATE_QUERY = "UPDATE users SET  first_name = ?, last_name = ?, age =?, dateOfBirth = ?, salary=? WHERE id = ?";


    private Connection connection;

    public JdbcUserDao(Connection connection){
        this.connection = connection;
    }


    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (Statement statement = connection.createStatement();
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

    public void add(User user){
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryAdd);) {

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
    public void delete(int id)  {
        try(PreparedStatement preparedStatement = connection.prepareStatement(delete);){

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            connection.commit();
            System.out.println("user deletion is successful");
        } catch (SQLException e) {
            System.out.println("[error] Unable to delete user");
            throw new RuntimeException(e);
        }
   }


   public void update(User user) {
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setLong(3, user.getAge());
            preparedStatement.setDate(4, Date.valueOf(user.getBirth()));
            preparedStatement.setLong(5, user.getSalary());
            preparedStatement.setLong(6, user.getId());

            preparedStatement.executeUpdate();
            connection.commit();

            System.out.println("User with id= {} was updated successful."+ user.getId() );

        } catch (SQLException e) {
            throw new RuntimeException("Can't update user with id = " + user.getId(), e);
        }
    }
}


