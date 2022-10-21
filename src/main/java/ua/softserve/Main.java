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
        BookDao bookDao = new BookDaoImpl();
        QuantityDao quantityDao = new QuantityDaoImpl();
        BookService service = new BookService(bookDao, authorDao, quantityDao);



//
//        List<Book> list = bookDao.findBookByAuthor("1");
//
//        for (Book b : list) {
//            System.out.println(b.getTitle());
//        }
//        Book book = service.getBook(1);

        List<Book> list = bookDao.findBookByTitle("nikita2");

        for (Book b : list) {
            System.out.println(b.getTitle());
        }

//        Author author2 = new Author();
//        author2.setName("nikita2");
//        author2.setSurname("korzh2");
//        authorDao.addAuthor(author2);
//        //CREATE BOOK
//
//
//        Author author = new Author();
//        author.setName("Jane");
//        author.setSurname("Austen");
//        authorDao.addAuthor(author);
//
//        Author coAuthor = new Author();
//        coAuthor.setName("Man2");
//        coAuthor.setSurname("Manovich2");
//        authorDao.addAuthor(coAuthor);
////
//        Book book = new Book();
//        book.setTitle("Java4");
//        book.setDescription("The best language x4");
//        book.setISBN("4114567894445");
//        book.setGenre(Genre.NOVEL);
//        book.setAuthor(authorDao.getAuthor(3));
//        //book.setCoAuthors(authorDao.getAuthor(3));
//        bookDao.addBook(book);



//        Author author = authorDao.getAuthor(book.getAuthor().getId());
//
//        System.out.println(author.getId() +
//                author.getName() + author.getSurname());



//        List<Book> lis  = bookDao.listBook();
////
//        for (Book b : lis) {
//            //System.out.println(authorDao.getAuthor(b.getAuthor().getId()).getName());
//            System.out.println(b.getId() + b.getAuthor().getName() + " " + b.getCoAuthors().getName());
//        }
//        //CREATE QUANTITY
          //quantityDao.addQuantity(book, 5);
//        quantityDao.deleteOneQuantity(9);
//        System.out.println(quantityDao.getCountOfQuantityByBookId(book.getId()));

        //DELETE BOOK WITH QUANTITY
        //bookDao.deleteAllCopiesBook(book.getId());
        //quantityDao.deleteOneQuantity(4);
    }
}
