package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserve.dao.AuthorDao;
import ua.softserve.dao.BookDao;
import ua.softserve.dao.QuantityDao;
import ua.softserve.dto.BookDto;
import ua.softserve.dto.CreateBookDto;
import ua.softserve.mapper.BookMapper;
import ua.softserve.mapper.CreateBookMapper;
import ua.softserve.model.Author;
import ua.softserve.model.Book;
import ua.softserve.model.Quantity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;
    private final QuantityDao quantityDao;

    @Autowired
    public BookService(BookDao bookDao, AuthorDao authorDao, QuantityDao quantityDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.quantityDao = quantityDao;
    }
    public void addBookWithMainAuthor(CreateBookDto bookDto) {
        Book book = CreateBookMapper.toModel(bookDto);
        Author author = authorDao.getOneAuthorByNameAndSurname(
                bookDto.getMainAuthorName(),
                bookDto.getMainAuthorSurname()
        );
        //Book book = CreateBookMapper.toModel(bookDto);
        if (author != null) {
            book.setAuthor(author);
            bookDao.addBook(book);
        } else {
            long authorId = authorDao.addAuthor(new Author(
                    bookDto.getMainAuthorName(),
                    bookDto.getMainAuthorSurname(),
                    Collections.emptyList()
            ));
            book.setAuthor(authorDao.getAuthor(authorId));
            //возможно нужно сохранить книгу в список книг в классе Author
            bookDao.addBook(book);
        }
    }
    public void addBookWithMainAuthorAndCoAuthor(CreateBookDto bookDto) {
        Book book = CreateBookMapper.toModel(bookDto);
        List<Author> authors = authorDao.getBothAuthorsByNameAndSurname(
                bookDto.getMainAuthorName(),
                bookDto.getMainAuthorSurname(),
                bookDto.getCoAuthorName(),
                bookDto.getCoAuthorSurname()
        );
        if (authors.size() == 2) {
            boolean isMainAuthorIndex0 = authors.get(0).getName().equals(bookDto.getMainAuthorName());
            long mainAuthorId;
            long coAuthorId;
            if (isMainAuthorIndex0) {
                mainAuthorId = authors.get(0).getId();
                coAuthorId = authors.get(1).getId();
            } else {
                mainAuthorId = authors.get(1).getId();
                coAuthorId = authors.get(0).getId();
            }
            book.setAuthor(authorDao.getAuthor(mainAuthorId));
            book.setCoAuthors(authorDao.getAuthor(coAuthorId));
            //дублирование
            bookDao.addBook(book);
            quantityDao.addQuantity(book, bookDto.getQuantity());
        }
        else if (authors.size() == 1) {
            boolean isExistingAuthorIsMain = authors.get(0).getSurname().equals(bookDto.getMainAuthorSurname());
            if (isExistingAuthorIsMain) {
                long coAuthorId = authorDao.addAuthor(new Author(
                        bookDto.getCoAuthorName(),
                        bookDto.getCoAuthorSurname(),
                        Collections.emptyList()
                ));
                //book.setAuthor(authorDao.getAuthor(authors.get(0).getId())
                book.setAuthor(authorDao.getOneAuthorByNameAndSurname(
                        bookDto.getMainAuthorName(),
                        bookDto.getMainAuthorSurname()));
                book.setCoAuthors(authorDao.getAuthor(coAuthorId));
            } else {
                long mainAuthorId = authorDao.addAuthor(new Author(
                        bookDto.getMainAuthorName(),
                        bookDto.getMainAuthorSurname(),
                        Collections.emptyList()
                ));
                book.setAuthor(authorDao.getAuthor(mainAuthorId));
                book.setCoAuthors(authorDao.getOneAuthorByNameAndSurname(
                        bookDto.getCoAuthorName(),
                        bookDto.getCoAuthorSurname()
                ));
                //дублирование
                bookDao.addBook(book);
                quantityDao.addQuantity(book, bookDto.getQuantity());
            }
        } else if (authors.size() == 0) {
            long mainAuthorId = authorDao.addAuthor(new Author(
                    bookDto.getMainAuthorName(),
                    bookDto.getMainAuthorSurname(),
                    Collections.emptyList()
            ));
            long coAuthorId = authorDao.addAuthor(new Author(
                    bookDto.getCoAuthorName(),
                    bookDto.getCoAuthorSurname(),
                    Collections.emptyList()
            ));
            book.setAuthor(authorDao.getAuthor(mainAuthorId));
            book.setCoAuthors(authorDao.getAuthor(coAuthorId));
            //дублирование
            bookDao.addBook(book);
            quantityDao.addQuantity(book, bookDto.getQuantity());
        }
    }

    public void deleteBook(long id) {
        bookDao.deleteAllCopiesBook(id);
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

    public void deleteOneQuantity(long bookId){
        quantityDao.deleteOneQuantity(bookId);
    }

    public long getCountOfQuantityByBookId(long bookId){
        return quantityDao.getCountOfQuantityByBookId(bookId);
    }



}
