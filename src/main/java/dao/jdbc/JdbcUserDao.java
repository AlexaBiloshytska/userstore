package dao.jdbc;

import dao.UserDao;
import dao.jdbc.mapper.UserMapper;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final String GET_ALL_SQL = "SELECT id, first_name, last_name, age, dateOfBirth,salary FROM USERS ";
    private static final String ADD_USER_SQL = "insert into users(first_name, last_name, age, dateOfBirth, salary) values(?,?,?,?,?)";
    private static final String DELETE_USER_SQL = "delete from users where id =?";
    private final static String UPDATE_USER_SQL = "UPDATE users SET  first_name = ?, last_name = ?, age =?, dateOfBirth = ?, salary=? WHERE id = ?";

    private Connection connection;

    public JdbcUserDao(Connection connection){
        this.connection = connection;
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_SQL);){
            while (resultSet.next()) {
                User user = USER_MAPPER.mapRow(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("[ERROR] Unable to process resultSet");
            throw new RuntimeException(e);
        }
    }

    public void add(User user){
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_SQL);) {
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
            throw new RuntimeException(e);
        }
    }
    public void delete(int id)  {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);){
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
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


