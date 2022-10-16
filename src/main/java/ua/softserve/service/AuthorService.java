package ua.softserve.service;

import ua.softserve.dao.AuthorDao;
import ua.softserve.model.Author;

public class AuthorService {

    AuthorDao authorDao;

    public AuthorService(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public void addAuthor(Author author){
        authorDao.addAuthor(author);
    }
}
