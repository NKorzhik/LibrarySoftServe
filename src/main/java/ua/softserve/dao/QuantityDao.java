package ua.softserve.dao;

import org.springframework.stereotype.Repository;
import ua.softserve.model.Book;


public interface QuantityDao {
    void addQuantity(Book book, int count);

    void deleteOneCopyById(long bookId);

    long getCountOfQuantityByBookId(long bookId);
}
