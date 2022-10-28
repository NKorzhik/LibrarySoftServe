package ua.softserve.dao;

import org.springframework.stereotype.Repository;
import ua.softserve.model.Book;
import ua.softserve.model.enums.Type;


public interface QuantityDao {
    void addQuantity(Book book, int count);
    long getFirstFreeCopyByBookId(long bookId);
    long getCountOfQuantityByBookId(long bookId);

    void changeTypeOfCopyById(long id, Type type);
    void deleteOneCopyById(long bookId);


}
