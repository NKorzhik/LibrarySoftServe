package ua.softserve.dao;

import ua.softserve.model.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);

    void deleteBook(long id);

    List<Book> listBook();

    Book getBook(long id);

    List<Book> findBookByTitle(String title);

    List<Book> getPopularBookInSelectedPeriod(String firstDate, String secondDate);

    List<Book> getUnpopularBookInSelectedPeriod(String firstDate, String secondDate);
}
