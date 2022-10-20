package ua.softserve.dao;

import ua.softserve.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao{

    long addAuthor(Author author);

    Author getAuthor(long id);

    List<Author> getBothAuthorsByNameAndSurname(String mainAuthorName,
                                            String mainAuthorSurname,
                                            String coAuthorName,
                                            String coAuthorSurname);
    Optional<Author> getOneAuthorByNameAndSurname(String name, String surname);
}
