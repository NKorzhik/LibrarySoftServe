package ua.softserve.dao;

import org.springframework.stereotype.Repository;
import ua.softserve.model.Book;

@Repository
public interface QuantityDao {
    void addQuantity(Book book, int count);

    void deleteOneQuantity(long id);
}
