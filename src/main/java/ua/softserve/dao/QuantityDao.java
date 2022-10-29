package ua.softserve.dao;

import ua.softserve.model.Book;


public interface QuantityDao {
    void addQuantity(Book book, int count);

    long getFirstFreeCopyByBookId(long bookId);

    long getCountOfQuantityByBookId(long bookId);

    void changeTypeOfCopyById(long id);

    void deleteOneCopyById(long bookId);


}
