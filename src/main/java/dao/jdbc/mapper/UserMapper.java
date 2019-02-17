package dao.jdbc.mapper;

import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper {

    public User mapRow(ResultSet resultSet) throws SQLException {
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

}
