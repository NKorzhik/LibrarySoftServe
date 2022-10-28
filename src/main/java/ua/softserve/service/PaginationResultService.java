package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserve.dao.PaginationDao;
import ua.softserve.model.Book;
import ua.softserve.model.PaginationResult;

@Service
public class PaginationResultService {
    PaginationDao paginationDao;

    @Autowired
    public PaginationResultService(PaginationDao paginationDao) {
        this.paginationDao = paginationDao;
    }

    public PaginationResult<Book> paginate(int pageNumber){
        return paginationDao.paginate(pageNumber);
    }
}
