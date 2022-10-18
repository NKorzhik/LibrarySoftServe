package ua.softserve;

import ua.softserve.dao.AuthorDao;
import ua.softserve.dao.BookDao;
import ua.softserve.dao.QuantityDao;
import ua.softserve.dao.impl.AuthorDaoImpl;
import ua.softserve.dao.impl.BookDaoImpl;
import ua.softserve.dao.impl.QuantityDaoImpl;
import ua.softserve.model.Author;
import ua.softserve.model.Book;
import ua.softserve.model.enums.Genre;
import ua.softserve.service.BookService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        AuthorDao authorDao = new AuthorDaoImpl();
       /* Author author = new Author();
        author.setName("nikita2");
        author.setSurname("korzh2");*/

        //CREATE BOOK
        BookDao bookDao = new BookDaoImpl();
        BookService bookService = new BookService(bookDao);

        Author author = new Author();
        author.setName("Man1");
        author.setSurname("Manovich1");
        //authorDao.addAuthor(author);

        Author coAuthor = new Author();
        coAuthor.setName("Man2");
        coAuthor.setSurname("Manovich2");
        //authorDao.addAuthor(coAuthor);

        Book book = new Book();
        book.setTitle("book10000");
        book.setDescription("fdfdfdf");
        book.setISBN("7114567894445");
        book.setGenre(Genre.ROMAN);
        book.setAuthor(authorDao.getAuthor(2));
        book.setCoAuthors(authorDao.getAuthor(1));
        bookDao.addBook(book);

//        List<Book> lis  = bookService.listBook();
//
//        for (Book b : lis) {
//            System.out.println(b.getId());
//        }
//        //CREATE QUANTITY
//        QuantityDao quantityDao = new QuantityDaoImpl();
        //quantityDao.addQuantity(book, 5);

        //DELETE BOOK WITH QUANTITY
        //bookDao.deleteAllCopiesBook(book.getId());
        //quantityDao.deleteOneQuantity(4);
    }
}
