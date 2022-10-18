package ua.softserve;

import ua.softserve.dao.BookDao;
import ua.softserve.dao.QuantityDao;
import ua.softserve.dao.impl.BookDaoImpl;
import ua.softserve.dao.impl.QuantityDaoImpl;
import ua.softserve.model.Book;
import ua.softserve.model.enums.Genre;
import ua.softserve.service.BookService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*AuthorDao authorDao = new AuthorDaoImpl();
        Author author = new Author();
        author.setName("nikita2");
        author.setSurname("korzh2");

        authorDao.addAuthor(author);
        System.out.println(author.getId());*/


        //CREATE BOOK
        BookDao bookDao = new BookDaoImpl();
        BookService bookService = new BookService(bookDao);
//        Book book = new Book();
//        book.setTitle("book3");
//        book.setDescription("I love Hibernate");
//        book.setISBN("6114567894445");
//        book.setGenre(Genre.ROMAN);
//        bookDao.addBook(book);

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
