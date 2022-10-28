package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserve.dao.AuthorDao;
import ua.softserve.dto.author.AuthorDto;
import ua.softserve.model.Author;

import java.util.Optional;

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

    public Optional<Author> getAuthorByNameAndSurname(AuthorDto authorDto){
        return authorDao.getAuthorByNameAndSurname(authorDto.getName(), authorDto.getSurname());
    }
}
