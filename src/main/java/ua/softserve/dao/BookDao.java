package ua.softserve.dao;

import ua.softserve.dto.BookDto;
import ua.softserve.model.Author;
import ua.softserve.model.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);
    void deleteBook(long id);
    List<Book> listBook();
    Book getBook(long id);
    List<Book> findBookByTitle(String title);
    List<Book> findBookByAuthor(long id);


}
