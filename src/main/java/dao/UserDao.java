package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    void delete(int id);

    void add(User user);

    void update(User user);
}
