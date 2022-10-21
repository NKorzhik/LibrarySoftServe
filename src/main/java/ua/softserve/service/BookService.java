package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserve.dao.AuthorDao;
import ua.softserve.dao.BookDao;
import ua.softserve.dao.QuantityDao;
import ua.softserve.dto.BookDto;
import ua.softserve.dto.BookCreateDto;
import ua.softserve.mapper.AuthorCreateMapper;
import ua.softserve.mapper.BookMapper;
import ua.softserve.mapper.BookCreateMapper;
import ua.softserve.model.Author;
import ua.softserve.model.Book;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final QuantityDao quantityDao;

    @Autowired
    public BookService(BookDao bookDao,
                       AuthorDao authorDao,
                       QuantityDao quantityDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.quantityDao = quantityDao;
    }
    public void addBookWithMainAuthor(BookCreateDto bookDto) {
        Book book = BookCreateMapper.toModel(bookDto);
        Optional<Author> author = authorDao.getAuthorByNameAndSurname(
                bookDto.getAuthorDto().getName(),
                bookDto.getAuthorDto().getSurname()
        );
        if (author.isPresent()) {
            book.setAuthor(author.get());
        } else {
            book.setAuthor(authorDao.addAuthor(AuthorCreateMapper
                    .mapToModel(bookDto.getAuthorDto())));
            bookDao.addBook(book);
        }
        bookDao.addBook(book);
        quantityDao.addQuantity(book, bookDto.getQuantity());
    }
    public void addBookWithMainAuthorAndCoAuthor(BookCreateDto bookDto) {
        Book book = BookCreateMapper.toModel(bookDto);
        Optional<Author> mainAuthor = authorDao.getAuthorByNameAndSurname(
                bookDto.getAuthorDto().getName(),
                bookDto.getAuthorDto().getSurname()
        );
        Optional<Author> coAuthor = authorDao.getAuthorByNameAndSurname(
                bookDto.getCoAuthorDto().getName(),
                bookDto.getCoAuthorDto().getSurname()
        );
        if (mainAuthor.isPresent()) {
            book.setAuthor(mainAuthor.get());
        } else {
            book.setAuthor(authorDao.addAuthor(AuthorCreateMapper
                    .mapToModel(bookDto.getAuthorDto())));
        }
        if (coAuthor.isPresent()) {
            book.setCoAuthors(coAuthor.get());
        } else {
            book.setCoAuthors(authorDao.addAuthor(AuthorCreateMapper
                    .mapToModel(bookDto.getCoAuthorDto())));
        }
        bookDao.addBook(book);
        quantityDao.addQuantity(book, bookDto.getQuantity());
    }

    public void deleteBook(long id) {
        bookDao.deleteBook(id);
    }

    public List<BookDto> listBook() {
        return bookDao.listBook().stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findBookByTitle(String title){
        return bookDao.findBookByTitle(title).stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    public Book getBook(long id){
        return bookDao.getBook(id);
    }

    public void addQuantity(Book book, int count){
        quantityDao.addQuantity(book, count);
    }
    public long getCountOfQuantityByBookId(long bookId){
        return quantityDao.getCountOfQuantityByBookId(bookId);
    }

}
