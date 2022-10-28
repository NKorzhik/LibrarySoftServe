package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserve.dao.QuantityDao;

@Service
public class QuantityService {
    private final QuantityDao quantityDao;
    @Autowired
    public QuantityService(QuantityDao quantityDao) {
        this.quantityDao = quantityDao;
    }
    public void deleteOneCopyById(long bookId) {
        quantityDao.deleteOneCopyById(bookId);
    }
}
