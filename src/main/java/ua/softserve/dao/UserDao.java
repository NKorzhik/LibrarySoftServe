package ua.softserve.dao;

import ua.softserve.model.HistoryOfRequest;
import ua.softserve.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findByEmail(String email);
    void addUser(User user);
    List<HistoryOfRequest> getRequestedBooks(long id);
    HistoryOfRequest getRequestById(long id);

//    List<HistoryOfRequest> findBook(String title);
}
