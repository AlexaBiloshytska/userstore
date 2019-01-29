package dao.mapper;

import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class userMapper {

    public static User mapRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setAge(resultSet.getLong("age"));
        user.setFirstName(resultSet.getString("first_name"));
        return user;
    }

}
