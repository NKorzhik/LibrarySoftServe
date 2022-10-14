package ua.softserve.dao;

import ua.softserve.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void update(User user);
    List<User> listUsers();
    void delete(User user);
}
