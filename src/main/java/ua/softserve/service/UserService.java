package ua.softserve.service;

import ua.softserve.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void update(User user);

    List<User> listUsers();

    void remoce(User user);
}
