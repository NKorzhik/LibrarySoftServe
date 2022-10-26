package ua.softserve.dao;

import ua.softserve.model.HistoryOfRequest;

import java.util.List;

public interface RequestDao {

    void addRequest(HistoryOfRequest request);
    List<HistoryOfRequest> getRequestedBooks(long id);
    HistoryOfRequest getRequestById(long id);
}
