package ua.softserve.dao;

import ua.softserve.model.HistoryOfRequest;

import java.time.LocalDate;
import java.util.List;

public interface RequestDao {

    void addRequest(HistoryOfRequest request);

    void acceptRequest(HistoryOfRequest request);
    List<HistoryOfRequest> getRequestedBooks(long id);
    HistoryOfRequest getRequestById(long id);
    List<HistoryOfRequest> getBooksWithStatusWaiting();

    void returnBookToLibrary(long bookId);
}
