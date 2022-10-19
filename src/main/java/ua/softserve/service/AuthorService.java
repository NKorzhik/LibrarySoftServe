package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserve.dao.AuthorDao;
import ua.softserve.model.Author;

@Service
public class AuthorService {

    AuthorDao authorDao;

    @Autowired
    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public void addAuthor(Author author){
        authorDao.addAuthor(author);
    }

    public Author getAuthor(long id){
        return authorDao.getAuthor(id);
    }
}
