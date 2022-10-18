package ua.softserve.dao;

import ua.softserve.model.Author;

public interface AuthorDao{

    void addAuthor(Author author);

    Author getAuthor(long id);

}
