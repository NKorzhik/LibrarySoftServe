package ua.softserve.dao;

import ua.softserve.model.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findByEmail(String email);
    void addUser(User user);
}
