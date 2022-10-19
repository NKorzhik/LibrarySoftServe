package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserve.dao.BookDao;
import ua.softserve.dao.QuantityDao;
import ua.softserve.model.Author;
import ua.softserve.model.Book;
import ua.softserve.model.Quantity;

import java.util.List;

@Service
public class BookService {

    private final BookDao bookDao;
    private final QuantityDao quantityDao;

    public BookService(BookDao bookDao, QuantityDao quantityDao) {
        this.bookDao = bookDao;
        this.quantityDao = quantityDao;
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

    public Book getBook(long id){
        return bookDao.getBook(id);
    }

    public void addQuantity(Book book, int count){
        quantityDao.addQuantity(book, count);
    }

    public void deleteOneQuantity(long bookId){
        quantityDao.deleteOneQuantity(bookId);
    }

    public long getCountOfQuantityByBookId(long bookId){
        return quantityDao.getCountOfQuantityByBookId(bookId);
    }
}
