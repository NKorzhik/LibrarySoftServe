package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserve.dao.BookDao;
import ua.softserve.model.Author;
import ua.softserve.model.Book;
import java.util.List;

@Service
public class BookService {

    private final BookDao bookDao;
    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    public void deleteBook(long id) {
        bookDao.deleteAllCopiesBook(id);
    }

    public List<Book> listBook() {
        return bookDao.listBook();
    }
}
