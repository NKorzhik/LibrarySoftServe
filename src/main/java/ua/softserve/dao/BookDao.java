package ua.softserve.dao;

import ua.softserve.model.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);
    void deleteAllCopiesBook(long id);
    void deleteOneCopyBook(long id);
    List<Book> listBook();
    Book getBook(long id);
}
