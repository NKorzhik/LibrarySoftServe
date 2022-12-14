package ua.softserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.softserve.dao.AuthorDao;
import ua.softserve.dao.BookDao;
import ua.softserve.dao.QuantityDao;
import ua.softserve.dto.book.BookReadUpdateDto;
import ua.softserve.dto.book.BookCreateDto;
import ua.softserve.mapper.author.AuthorMapper;
import ua.softserve.mapper.book.BookReadUpdateMapper;
import ua.softserve.mapper.book.BookCreateMapper;
import ua.softserve.model.Author;
import ua.softserve.model.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    //private final AuthorService authorService;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final QuantityDao quantityDao;

    @Autowired
    public BookService(/*AuthorService authorService,*/
                       BookDao bookDao,
                       AuthorDao authorDao,
                       QuantityDao quantityDao) {
        //this.authorService = authorService;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.quantityDao = quantityDao;
    }
    public void addBookWithMainAuthor(BookCreateDto bookDto) {
        //перенести строку с преобразованием в dto в самый конец
        Book book = BookCreateMapper.mapToModel(bookDto);
        Optional<Author> author = authorDao.getAuthorByNameAndSurname(
                bookDto.getAuthorDto().getName(),
                bookDto.getAuthorDto().getSurname()
        );
       /* Optional<Author> author = authorService.getAuthorByNameAndSurname(
                bookDto.getAuthorDto());*/
        if (author.isPresent()) {
            book.setAuthor(author.get());
        } else {
            book.setAuthor(authorDao.addAuthor(AuthorMapper
                    .mapToModel(bookDto.getAuthorDto())));
        }
        bookDao.addBook(book);
        quantityDao.addQuantity(book, bookDto.getQuantity());
    }
    public void addBookWithMainAuthorAndCoAuthor(BookCreateDto bookDto) {
        //перенести строку с преобразованием в dto в самый конец
        Book book = BookCreateMapper.mapToModel(bookDto);
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
            book.setAuthor(authorDao.addAuthor(AuthorMapper
                    .mapToModel(bookDto.getAuthorDto())));
        }
        if (coAuthor.isPresent()) {
            book.setCoAuthor(coAuthor.get());
        } else {
            book.setCoAuthor(authorDao.addAuthor(AuthorMapper
                    .mapToModel(bookDto.getCoAuthorDto())));
        }
        bookDao.addBook(book);
        quantityDao.addQuantity(book, bookDto.getQuantity());
    }

    public void updateBook(BookReadUpdateDto bookDto, long id) {
        Book book = bookDao.getBook(id);
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
            book.setAuthor(authorDao.addAuthor(AuthorMapper
                    .mapToModel(bookDto.getAuthorDto())));
        }
        if (coAuthor.isPresent()) {
            book.setCoAuthor(coAuthor.get());
        } else {
            book.setCoAuthor(authorDao.addAuthor(AuthorMapper
                    .mapToModel(bookDto.getCoAuthorDto())));
        }
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setGenre(bookDto.getGenre());
        book.setISBN(bookDto.getISBN());
        bookDao.updateBook(book);
        quantityDao.addQuantity(book, bookDto.getQuantity());
    }

    public void deleteBook(long id) {
        bookDao.deleteBook(id);
    }

    public List<BookReadUpdateDto> listBook() {
        return bookDao.listBook().stream()
                .map(BookReadUpdateMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<BookReadUpdateDto> findBookByTitle(String title){
        return bookDao.findBookByTitle(title).stream()
                .map(BookReadUpdateMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public BookReadUpdateDto getBook(long id){
        return Optional.of(bookDao.getBook(id))
                .map(BookReadUpdateMapper::mapToDto)
                .orElseThrow();
    }
    
    public void addQuantity(Book book, int count){
        quantityDao.addQuantity(book, count);
    }
    public long getCountOfQuantityByBookId(long bookId){
        return quantityDao.getCountOfQuantityByBookId(bookId);
    }

    public List<Book> getPopularBookInSelectedPeriod(String firstDate, String secondDate){
        return bookDao.getPopularBookInSelectedPeriod(firstDate,secondDate);
    }

    public List<Book> getUnpopularBookInSelectedPeriod(String firstDate, String secondDate){
        return bookDao.getUnpopularBookInSelectedPeriod(firstDate,secondDate);
    }

}
