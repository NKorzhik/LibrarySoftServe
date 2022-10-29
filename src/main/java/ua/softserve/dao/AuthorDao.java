package ua.softserve.dao;

import ua.softserve.model.Author;

import java.util.Optional;

public interface AuthorDao {

    Author addAuthor(Author author);

    Author getAuthor(long id);

    Optional<Author> getAuthorByNameAndSurname(String name, String surname);
}
