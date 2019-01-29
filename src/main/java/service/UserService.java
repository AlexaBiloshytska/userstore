package service;
import entity.User;

import java.util.List;
public interface UserService {

     List<User> getAll();

    void  add(User user);

    void delete(int id);

    void update(User user);

}
