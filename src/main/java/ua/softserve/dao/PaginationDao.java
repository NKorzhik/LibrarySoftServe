package ua.softserve.dao;

import ua.softserve.model.Book;
import ua.softserve.model.PaginationResult;

public interface PaginationDao {

    PaginationResult<Book> paginate(int pageNumber);

}
